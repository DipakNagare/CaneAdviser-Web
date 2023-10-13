package com.cdac.caneadviser.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cdac.caneadviser.entity.Queryhandler;

@Repository
public interface QueryhandlerRepo extends JpaRepository<Queryhandler, Integer> {
	
	
	 @Query(value = "SELECT MONTH(STR_TO_DATE(q.asked_date, '%d-%m-%Y')) AS month, COUNT(q.asked_date) AS count " +
             "FROM Queryhandler q " +
             "WHERE YEAR(STR_TO_DATE(q.asked_date, '%d-%m-%Y')) = YEAR(NOW()) " +
             "GROUP BY MONTH(STR_TO_DATE(ASKED_DATE, '%d-%m-%Y'))", nativeQuery = true)
	 
    List<Object[]> getMonthlyCountsForCurrentYear();
	
    List<Queryhandler> findByFarmerDetailFarmId(int farmId);

    List<Queryhandler> findByQueryAnswerIsNotNull();

    List<Queryhandler> findByQueryAnswerIsNull();

    List<Queryhandler> findByFarmerDetailFarmIdAndQueryAnswerIsNotNull(int farmId);

    List<Queryhandler> findByFarmerDetailFarmIdAndQueryAnswerIsNull(int farmId);

    @Query(value ="SELECT coalesce(max(qh.que_id), 0) FROM queryhandler qh", nativeQuery = true)
	Integer maxNumber();

	List<Queryhandler> findAllByFarmerDetailFarmId(int farmId);

	

	//int maxNumber();
}
