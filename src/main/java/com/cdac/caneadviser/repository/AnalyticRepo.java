package com.cdac.caneadviser.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cdac.caneadviser.entity.Analytic;

@Repository
public interface AnalyticRepo extends JpaRepository<Analytic, Integer> {
	
	//List<Analytic> findAll();

	 @Query(value = "SELECT accContent, COUNT(*) AS count FROM analytics GROUP BY accContent", nativeQuery = true)
	    List<Object[]> getTechnologyWiseCount();
		

	
}