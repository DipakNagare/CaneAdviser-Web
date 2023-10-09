package com.cdac.caneadviser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.caneadviser.entity.FarmerDetail;

@Repository
public interface FarmerDetailRepo extends JpaRepository<FarmerDetail, Integer> {

	List<FarmerDetail> findByMobileNo(String mobileNo);
   
}
