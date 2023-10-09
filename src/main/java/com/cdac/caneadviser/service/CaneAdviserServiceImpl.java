package com.cdac.caneadviser.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cdac.caneadviser.dao.Login;
import com.cdac.caneadviser.dao.Registration;
import com.cdac.caneadviser.dao.VerifyOTP;
import com.cdac.caneadviser.entity.Analytic;
import com.cdac.caneadviser.entity.FarmerDetail;
import com.cdac.caneadviser.entity.GroupMaster;
import com.cdac.caneadviser.entity.MobileUserHit;
import com.cdac.caneadviser.entity.Queryhandler;
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




import org.springframework.web.multipart.MultipartFile;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Service
public class CaneAdviserServiceImpl implements CaneAdviserService {

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
	private List<Queryhandler> queryHandlers;

	    public CaneAdviserServiceImpl(List<Queryhandler> queryHandlers) {
	        this.queryHandlers = queryHandlers;
	    }


	public Map<Integer, Map<Integer, List<Queryhandler>>> getQueriesYearMonthWise(int year) {
		Map<Integer, Map<Integer, List<Queryhandler>>> result = new HashMap<>();

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			for (Queryhandler handler : queryHandlers) {
				try {
					Date askedDate = dateFormat.parse(handler.getAskedDate());
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(askedDate);
					int handlerYear = calendar.get(Calendar.YEAR);
					int handlerMonth = calendar.get(Calendar.MONTH) + 1;

					if (handlerYear == year) {

						result.putIfAbsent(handlerYear, new HashMap<>());

						Map<Integer, List<Queryhandler>> yearMap = result.get(handlerYear);

						yearMap.putIfAbsent(handlerMonth, new ArrayList<>());

						yearMap.get(handlerMonth).add(handler);
					}
				} catch (ParseException e) {

					e.printStackTrace();
				}
			}
		} catch (IllegalArgumentException e) {

			e.printStackTrace();
		}

		return result;
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

	@Override
	public List<Analytic> getAllAnalytics() {
		return analyticRepo.findAll();
	}

	@Override
	public Analytic saveAnalytic(Analytic analytic) {
		return analyticRepo.save(analytic);
	}
	@Override
	public long countByAccContent(String accContent) {
	        return analyticRepo.countByAccContent(accContent);
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
	
	/*
	 * @Autowired private QueryAssignedMasterRepo queryAssignedMasterRepo;
	 * 
	 * private String adminEmailId = "cdacmlearn@gmail.com";
	 * 
	 * @Override public String queryHandler(Queryhandler queryHandler) { try {
	 * String image1Base64 = processImage(queryHandler.getImage1()); String
	 * image2Base64 = processImage(queryHandler.getImage2()); String image3Base64 =
	 * processImage(queryHandler.getImage3());
	 * 
	 * // Saving details to Query Handler Database int maxNo =
	 * queryhandlerRepo.maxNumber() + 1; FarmerDetail farmerDetail =
	 * farmerDetailRepo.findByFarmId(Integer.parseInt(queryHandler.getFarmId())).get
	 * (0);
	 * 
	 * Queryhandler queryhandlerEntity = new Queryhandler();
	 * queryhandlerEntity.setQueId(maxNo);
	 * queryhandlerEntity.setQuery(Jsoup.clean(queryHandler.getQuery(),
	 * Whitelist.none())); queryhandlerEntity.setFarmerDetail(farmerDetail);
	 * queryhandlerEntity.setImage1(image1Base64);
	 * queryhandlerEntity.setImage2(image2Base64);
	 * queryhandlerEntity.setImage3(image3Base64);
	 * queryhandlerEntity.setAnsweredBy("NA");
	 * queryhandlerEntity.setAnsweredDate("NA"); queryhandlerEntity.setAskedDate(new
	 * Date().toString()); queryhandlerEntity.setQueryAnswer("NA");
	 * queryhandlerEntity.setQueryDesc("NA");
	 * 
	 * queryhandlerRepo.save(queryhandlerEntity);
	 * 
	 * // Saving details to Query Assigned Master Database Queryhandler
	 * queryhandler1 = new Queryhandler(); queryhandler1.setQueId(maxNo);
	 * 
	 * QueryAssignedMaster queryAssignedMaster = new QueryAssignedMaster();
	 * queryAssignedMaster.setQueryhandler(queryhandler1);
	 * queryAssignedMaster.setAssignedId(maxNo);
	 * 
	 * UserMaster userMaster = new UserMaster();
	 * userMaster.setUserId("5UwLRJfxzHr/c1KCzl9NAg==");
	 * queryAssignedMaster.setUserMaster(userMaster);
	 * queryAssignedMaster.setStatus("Unanswered");
	 * 
	 * queryAssignedMasterRepo.save(queryAssignedMaster);
	 * 
	 * // Sending Mail To Administrator String farmerName =
	 * farmerDetail.getFarmerName(); generateSendOTP.sendEmailAdmin(adminEmailId,
	 * maxNo, Jsoup.clean(queryHandler.getQuery(), Whitelist.none()), farmerName);
	 * 
	 * return "Success"; } catch (IOException e) { e.printStackTrace(); return
	 * "Failed"; } }
	 * 
	 * private String processImage(MultipartFile multipart) throws IOException { if
	 * (multipart != null && checkMimeType(multipart)) { byte[] bytes =
	 * multipart.getBytes(); return new String(Base64.encodeBase64String(bytes)); }
	 * else { return "NA"; } }
	 * 
	 * private boolean checkMimeType(MultipartFile multipart) { Tika tika = new
	 * Tika(); try { String mediaType = tika.detect(multipart.getBytes()); return
	 * mediaType.startsWith("image/"); } catch (IOException e) {
	 * e.printStackTrace(); } return false; }
	 */
}

