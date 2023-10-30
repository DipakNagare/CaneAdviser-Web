package com.cdac.caneadviser.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.jsoup.Jsoup;


import com.cdac.caneadviser.dao.Login;
import com.cdac.caneadviser.dao.QueryViewDao;
import com.cdac.caneadviser.dao.Queryhandlerdao;
import com.cdac.caneadviser.dao.Registration;
import com.cdac.caneadviser.dao.VerifyOTP;
import com.cdac.caneadviser.entity.Analytic;
import com.cdac.caneadviser.entity.FarmerDetail;
import com.cdac.caneadviser.entity.GroupMaster;
import com.cdac.caneadviser.entity.MobileUserHit;
import com.cdac.caneadviser.entity.Queryhandler;
import com.cdac.caneadviser.entity.RoleMaster;
import com.cdac.caneadviser.entity.UserMaster;
import com.cdac.caneadviser.repository.AnalyticRepo;
import com.cdac.caneadviser.repository.FarmerDetailRepo;
import com.cdac.caneadviser.repository.GroupMasterRepo;
import com.cdac.caneadviser.repository.MobileUserHitRepo;
import com.cdac.caneadviser.repository.QueryAssignedMasterRepo;
import com.cdac.caneadviser.repository.QueryhandlerRepo;
import com.cdac.caneadviser.repository.UserMasterRepo; 

import org.springframework.data.domain.PageImpl;
import com.cdac.caneadviser.mail.GenerateSendOTP;
import org.springframework.beans.factory.annotation.Value; 



import org.springframework.web.multipart.MultipartFile;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.tika.Tika;







@Service
public class CaneAdviserServiceImpl implements CaneAdviserService {
	
	
	
	@Value("${spring.mail.username}")
	private String adminEmailId;


	@Autowired
	private GroupMasterRepo groupMasterRepo;

	@Override
	public List<GroupMaster> getAllGroupMasters() {
		return groupMasterRepo.findAll();
	}

	@Override
	public Optional<GroupMaster> getGroupMasterById(int groupId) {
		return groupMasterRepo.findById(groupId);
	}

	@Override
	public GroupMaster saveGroupMaster(GroupMaster groupMaster) {
		return groupMasterRepo.save(groupMaster);
	}

	@Override
	public void deleteGroupMaster(int groupId) {
		groupMasterRepo.deleteById(groupId);
	}

	@Override
	public GroupMaster updateGroupMaster(int groupId, GroupMaster updatedGroupMaster) {
		Optional<GroupMaster> existingGroupMaster = groupMasterRepo.findById(groupId);

		if (existingGroupMaster.isPresent()) {
			GroupMaster groupMaster = existingGroupMaster.get();
			groupMaster.setGroupName(updatedGroupMaster.getGroupName());
			groupMaster.setGroupDescription(updatedGroupMaster.getGroupDescription());
			return groupMasterRepo.save(groupMaster);
		} else {

			throw new RuntimeException("GroupMaster with ID " + groupId + " not found");
		}
	}

	@Autowired
	private UserMasterRepo userMasterRepo;

	@Override
	public List<UserMaster> getAllUserMasters() {
		return userMasterRepo.findAll();
	}

	@Override
	public Optional<UserMaster> getUserMasterById(String userId) {
		return userMasterRepo.findById(userId);
	}

	@Override
	public UserMaster saveUser(UserMaster user) {
		RoleMaster roleMaster =new RoleMaster();
		roleMaster.setRoleId(2);  //TODO - static value of expert role
		user.setRoleMaster(roleMaster);
		return userMasterRepo.save(user);
	}

	@Override
	public void deleteUser(String userId) {
		userMasterRepo.deleteById(userId);
	}

	@Override
	public UserMaster updateUser(UserMaster user) {
		return userMasterRepo.save(user);
	}
	
	@Autowired
	private QueryhandlerRepo queryhandlerRepo; 
	@Override
	public List<Object[]> getMonthlyCountsForCurrentYear() {
	    List<Object[]> monthlyCounts = queryhandlerRepo.getMonthlyCountsForCurrentYear();
	    return monthlyCounts;
	}

    
	@Override
	public List<Queryhandler> getAllQueries() {
		return queryhandlerRepo.findAll();
	}

	@Override
	public List<Queryhandler> getAnsweredQueries() {
		return queryhandlerRepo.findByQueryAnswerIsNotNull();
	}

	@Override
	public List<Queryhandler> getUnansweredQueries() {
		return queryhandlerRepo.findByQueryAnswerIsNull();
	}

	@Override
	public List<Queryhandler> getAnsweredAssignedQueries(int userId) {
		return queryhandlerRepo.findByFarmerDetailFarmIdAndQueryAnswerIsNotNull(userId);
	}

	@Override
	public List<Queryhandler> getUnansweredAssignedQueries(int userId) {
		return queryhandlerRepo.findByFarmerDetailFarmIdAndQueryAnswerIsNull(userId);
	}

	@Override
	public List<Queryhandler> getQueriesByFarmerId(int farmId) {
		return queryhandlerRepo.findByFarmerDetailFarmId(farmId);
	}

	@Override
	public Page<Queryhandler> getQueriesPagination(Pageable pageable) {
		pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

		Page<Queryhandler> page = queryhandlerRepo.findAll(pageable);

		List<Queryhandler> queryHandlerList = page.getContent();

		queryHandlerList = queryHandlerList.stream().filter(queryHandler -> queryHandler.getAskedDate() != null)
				.collect(Collectors.toList());

		Collections.sort(queryHandlerList, new Comparator<Queryhandler>() {
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

			@Override
			public int compare(Queryhandler q1, Queryhandler q2) {
				try {
					Date date1 = df.parse(q1.getAskedDate());
					Date date2 = df.parse(q2.getAskedDate());
					return date1.compareTo(date2);
				} catch (ParseException e) {
					throw new IllegalArgumentException(e);
				}
			}
		});

		Page<Queryhandler> sortedPage = new PageImpl<>(queryHandlerList, pageable, page.getTotalElements());

		SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");

		for (Queryhandler queryHandler : sortedPage.getContent()) {
			try {
				String dateString = queryHandler.getAskedDate();
				Date parsedDate = inputDateFormat.parse(dateString);
				String formattedDate = outputDateFormat.format(parsedDate);
				System.out.println(formattedDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return sortedPage;
	}

	@Autowired
	private FarmerDetailRepo farmerDetailRepo;

	@Override
	public List<FarmerDetail> getAllFarmerDetails() {
		return farmerDetailRepo.findAll();
	}

	@Override
	public Optional<FarmerDetail> getFarmerDetailById(int farmId) {
		return farmerDetailRepo.findById(farmId);
	}

	@Override
	public FarmerDetail saveFarmerDetail(FarmerDetail farmerDetail) {
		return farmerDetailRepo.save(farmerDetail);
	}

	@Override
	public void deleteFarmerDetail(int farmId) {
		farmerDetailRepo.deleteById(farmId);
	}

	@Override
	public FarmerDetail updateFarmerDetail(FarmerDetail farmerDetail) {
		return farmerDetailRepo.save(farmerDetail);
	}

	@Autowired
	private MobileUserHitRepo mobileUserHitRepo;

	@Override
	public List<MobileUserHit> getAllMobileUserHits() {
		return mobileUserHitRepo.findAll();
	}

	@Override
	public MobileUserHit saveMobileUserHit(MobileUserHit mobileUserHit) {
		return mobileUserHitRepo.save(mobileUserHit);
	}

	@Override
	public long getOverallCount() {
		return mobileUserHitRepo.count();
	}

	@Autowired
	private AnalyticRepo analyticRepo;
	
	 @Autowired
	    public CaneAdviserServiceImpl(AnalyticRepo analyticRepo) {
	        this.analyticRepo = analyticRepo;
	    }

	 public List<Object[]> getTechnologyWiseCount() {
	        return analyticRepo.getTechnologyWiseCount();
	    }
	@Override
	public List<Analytic> getAllAnalytics() {
		return analyticRepo.findAll();
	}
	 
	 
	@Autowired
	private GenerateSendOTP generateSendOTP;

	@Override
	public FarmerDetail checkAuthentication(Login login) {
		try {
			if (mobileAvailability(login.getMobileno())) {
				if (generateSendOTP.sendEmailSMS(
						farmerDetailRepo.findByMobileNo(login.getMobileno()).get(0).getEmailId(), login.getMobileno(),
						login.getImeino())) {
					FarmerDetail farmerDetail = new FarmerDetail();
					farmerDetail.setStatus("OTP");
					farmerDetail.setMobileNo(login.getMobileno());
					return farmerDetail;
				} else {
					FarmerDetail farmerDetail = new FarmerDetail();
					farmerDetail.setStatus("Fail");
					return farmerDetail;
				}
			} else {
				FarmerDetail farmerDetail = new FarmerDetail();
				farmerDetail.setStatus("No_Account");
				return farmerDetail;
			}
		} catch (Exception e) {
			e.printStackTrace();
			FarmerDetail errorDetail = new FarmerDetail();
			errorDetail.setStatus("Error");
			return errorDetail;
		}
	}

	@Override
	public FarmerDetail registerFarmer(Registration registration) {
		try {
			List<FarmerDetail> farmerList = farmerDetailRepo.findByMobileNo(registration.getMobileNo());

			if (farmerList.isEmpty()) {
				FarmerDetail farmerDetail = new FarmerDetail();

				farmerDetailRepo.save(farmerDetail);

				farmerDetail.setStatus("Success");
				return farmerDetail;
			} else {
				FarmerDetail farmerDetail = new FarmerDetail();
				farmerDetail.setStatus("Already_Reg");
				return farmerDetail;
			}
		} catch (Exception e) {
			e.printStackTrace();
			FarmerDetail errorDetail = new FarmerDetail();
			errorDetail.setStatus("Error");
			return errorDetail;
		}
	}

	@Override
	public Boolean mobileAvailability(String mobileno) {
		List<FarmerDetail> mobilenoList = farmerDetailRepo.findByMobileNo(mobileno);
		return !mobilenoList.isEmpty();
	}

	@Override
	public FarmerDetail verifyOTP(VerifyOTP verifyOTP) {
		try {
			FarmerDetail farmerDetail = farmerDetailRepo.findByMobileNo(verifyOTP.getMobileno()).get(0);

			if (verifyOTP.getOtp().trim().equals(farmerDetail.getOtp())) {

				String farmerID = Integer.toString(farmerDetail.getFarmId());

				farmerDetail.setImeiNo(verifyOTP.getImei());

				farmerDetail.setOtp("");
				farmerDetailRepo.save(farmerDetail);

				farmerDetail.setStatus("Success");

				farmerDetail.setFarmId(Integer.parseInt(farmerID));

				return farmerDetail;
			} else {

				farmerDetail.setStatus("Fail");
				farmerDetail.setFarmId(0);

				return farmerDetail;
			}
		} catch (Exception e) {
			e.printStackTrace();
			FarmerDetail errorDetail = new FarmerDetail();
			errorDetail.setStatus("Error");
			errorDetail.setFarmId(0);
			return errorDetail;
		}
	}
	
	
	@Autowired
	private QueryAssignedMasterRepo queryAssignedMasterRepo;
	
	
	@Autowired
	private Queryhandlerdao queryhandlerdao;
	
	@Override
	public String queryHandler(Queryhandler queryHandler) {
	    byte[] bytes;
	    String image1 = null;
	    String image2 = null;
	    String image3 = null;
	    int maxNo = 0;

	    try {
	        if (queryHandler.getImage1() != null && checkMimeType(queryhandlerdao.getImage1())) {
	            bytes = queryHandler.getImage1().getBytes();
	            image1 = new String(Base64.getEncoder().encode(bytes));
	        } else {
	            image1 = "NA";
	        }

	        if (queryHandler.getImage2() != null && checkMimeType(queryhandlerdao.getImage2())) {
	            bytes = queryHandler.getImage2().getBytes();
	            image2 = new String(Base64.getEncoder().encode(bytes));
	        } else {
	            image2 = "NA";
	        }

	        if (queryHandler.getImage3() != null && checkMimeType(queryhandlerdao.getImage3())) {
	            bytes = queryHandler.getImage3().getBytes();
	            image3 = new String(Base64.getEncoder().encode(bytes));
	        } else {
	            image3 = "NA";
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        return "Failed";
	    }

	    com.cdac.caneadviser.entity.Queryhandler qh = new com.cdac.caneadviser.entity.Queryhandler();
	    com.cdac.caneadviser.entity.QueryAssignedMaster queryAssignedMaster = new com.cdac.caneadviser.entity.QueryAssignedMaster();
	    com.cdac.caneadviser.entity.FarmerDetail farmerDetail = new com.cdac.caneadviser.entity.FarmerDetail();

	    maxNo = queryhandlerRepo.maxNumber() + 1;

	    farmerDetail.setFarmId(Integer.parseInt(queryhandlerdao.getFarmId()));
	    qh.setQuery(Jsoup.clean(queryhandlerdao.getQuery(), Whitelist.none()));
	    qh.setFarmerDetail(farmerDetail);
	    qh.setImage1(image1);
	    qh.setImage2(image2);
	    qh.setImage3(image3);
	    qh.setQueId(maxNo);
	    qh.setAnsweredBy("NA");
	    qh.setAnsweredDate("NA");
	    qh.setAskedDate(new Date().toString());
	    qh.setQueryAnswer("NA");
	    qh.setQueryDesc("NA");
	    queryhandlerRepo.save(qh);

	    com.cdac.caneadviser.entity.Queryhandler qh1 = new com.cdac.caneadviser.entity.Queryhandler();
	    qh1.setQueId(maxNo);
	    queryAssignedMaster.setQueryhandler(qh1);
	    queryAssignedMaster.setAssignedId(maxNo);

	    UserMaster um = new UserMaster();
	    um.setUserId("5UwLRJfxzHr/c1KCzl9NAg==");
	    queryAssignedMaster.setUserMaster(um);
	    queryAssignedMaster.setStatus("Unanswered");
	    queryAssignedMasterRepo.save(queryAssignedMaster);

	    String farmerName = farmerDetailRepo.findByFarmId(Integer.parseInt(queryhandlerdao.getFarmId())).get(0).getFarmerName();
	    generateSendOTP.sendEmailAdmin(adminEmailId, maxNo, Jsoup.clean(queryHandler.getQuery(), Whitelist.none()), farmerName);

	    return "Success";
	}

	private boolean checkMimeType(MultipartFile multipart) {
	    Tika tika = new Tika();
	    try {
	        String mediaType = tika.detect(multipart.getBytes());
	        if (mediaType.equalsIgnoreCase("image/*") || mediaType.equalsIgnoreCase("image/jpg") ||
	                mediaType.equalsIgnoreCase("image/jpeg") || mediaType.equalsIgnoreCase("image/png")) {
	            return true;
	        }
	    } catch (Exception e) {}
	    return false;
	}
	
	@Override
	public List<QueryViewDao> getQueryByFarmId(int farmId) {
		
		List<com.cdac.caneadviser.entity.Queryhandler> listQueryHandler = queryhandlerRepo.findAllByFarmerDetailFarmId(farmId);
		
		List<QueryViewDao> listQueryViewDao= new ArrayList<>();
		QueryViewDao queryViewDao;
		
		for(com.cdac.caneadviser.entity.Queryhandler query: listQueryHandler)
		{
			queryViewDao = new QueryViewDao();
			queryViewDao.setAnswer(query.getQueryAnswer());
			queryViewDao.setAskedDate(query.getAskedDate());
			queryViewDao.setQuery(query.getQuery());
			queryViewDao.setImage1(query.getImage1());
			listQueryViewDao.add(queryViewDao);
			
		}
		
		return listQueryViewDao;
	}

	@Override
	public Map<Integer, Map<Integer, List<Queryhandler>>> getQueriesYearMonthWise(int year) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getQueriesYearMonthWise'");
	}

	@Override
	public Analytic saveAnalytic(Analytic analytic) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'saveAnalytic'");
	}

	@Override
	public String queryHandler(Queryhandler queryHandler, String adminEmailId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'queryHandler'");
	}

	@Override
	public String MobileUserHit(com.cdac.caneadviser.entity.MobileUserHit mobileHit) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'MobileUserHit'");
	}

	@Override
	public long getCountTechnologywise(String technology) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getCountTechnologywise'");
	}

	@Override
	public void countByAccContent(String accContent) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'countByAccContent'");
	}


	
}