package com.cdac.caneadviser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.caneadviser.entity.FarmerDetail;

@Repository
public interface FarmerDetailRepo extends JpaRepository<FarmerDetail, Integer> {
    
    // @Query("SELECT f FROM FarmerDetail WHERE f.mobileNo = :mobileNo")
	List<FarmerDetail> findByMobileNo(String mobileNo);

//    @Query("SELECT f FROM FarmerDetail WHERE f.farmId = :parseInt")
    List<FarmerDetail> findByFarmId(int parseInt);
   
}
