package com.cdac.caneadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.caneadviser.entity.Analytic;

@Repository
public interface AnalyticRepo extends JpaRepository<Analytic, Integer> {

	long countByAccContent(String accContent);
	

	
}

