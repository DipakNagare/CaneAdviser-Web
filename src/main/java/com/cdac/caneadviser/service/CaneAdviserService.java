package com.cdac.caneadviser.service;

import java.util.List;

import java.util.Map;
import java.util.Optional;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cdac.caneadviser.dao.Login;
import com.cdac.caneadviser.dao.Registration;
import com.cdac.caneadviser.dao.VerifyOTP;
import com.cdac.caneadviser.entity.Analytic;
import com.cdac.caneadviser.entity.FarmerDetail;
import com.cdac.caneadviser.entity.GroupMaster;
import com.cdac.caneadviser.entity.MobileUserHit;
import com.cdac.caneadviser.entity.Queryhandler;
import com.cdac.caneadviser.entity.UserMaster;

public interface CaneAdviserService {
	
	
	//Group Master
	List<GroupMaster> getAllGroupMasters();
	Optional<GroupMaster> getGroupMasterById(int groupId);
    GroupMaster saveGroupMaster(GroupMaster groupMaster);
    void deleteGroupMaster(int groupId);
    GroupMaster updateGroupMaster(int groupId, GroupMaster updatedGroupMaster);
    
    
    //User Master
    List<UserMaster> getAllUserMasters();
    Optional<UserMaster> getUserMasterById(String userId);
    UserMaster saveUser(UserMaster user);
    void deleteUser(String userId);
    UserMaster updateUser(UserMaster user);

    
    
    //Query handler
    Map<Integer, Map<Integer, List<Queryhandler>>> getQueriesYearMonthWise(int year);
    List<Queryhandler> getAllQueries();
    List<Queryhandler> getAnsweredQueries();
    List<Queryhandler> getUnansweredQueries();
    List<Queryhandler> getAnsweredAssignedQueries(int userId);
    List<Queryhandler> getUnansweredAssignedQueries(int userId);
    List<Queryhandler> getQueriesByFarmerId(int farmId);
    String queryHandler(Queryhandler queryHandler);
    
    //Farmer Details
    List<FarmerDetail> getAllFarmerDetails();
    Optional<FarmerDetail> getFarmerDetailById(int farmId);
    FarmerDetail saveFarmerDetail(FarmerDetail farmerDetail);
    void deleteFarmerDetail(int farmId);
    FarmerDetail updateFarmerDetail(FarmerDetail farmerDetail);
    
    //MobileUserHit
    List<MobileUserHit> getAllMobileUserHits();
    MobileUserHit saveMobileUserHit(MobileUserHit mobileUserHit);
    long getOverallCount();
    
    //Analytic
    List<Analytic> getAllAnalytics();
    Analytic saveAnalytic(Analytic analytic);
	String queryHandler(Queryhandler queryHandler, String adminEmailId);
	String MobileUserHit(MobileUserHit mobileHit);
	long getCountTechnologywise(String technology);
	
	
	
	FarmerDetail verifyOTP(VerifyOTP verifyOTP);
	FarmerDetail checkAuthentication(Login login);
    FarmerDetail registerFarmer(Registration registration);
    Boolean mobileAvailability(String mobileno);
    
    
	Page<Queryhandler> getQueriesPagination(Pageable pageable);
	 //Map<String, Long> getCountByAccContentType();
	long countByAccContent(String accContent);
	

	
	
	

}
