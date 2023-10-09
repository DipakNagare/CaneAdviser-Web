package com.cdac.caneadviser.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.caneadviser.entity.Queryhandler;

@Repository
public interface QueryhandlerRepo extends JpaRepository<Queryhandler, Integer> {
	
    List<Queryhandler> findByFarmerDetailFarmId(int farmId);

    List<Queryhandler> findByQueryAnswerIsNotNull();

    List<Queryhandler> findByQueryAnswerIsNull();

    List<Queryhandler> findByFarmerDetailFarmIdAndQueryAnswerIsNotNull(int farmId);

    List<Queryhandler> findByFarmerDetailFarmIdAndQueryAnswerIsNull(int farmId);

	//int maxNumber();
}
