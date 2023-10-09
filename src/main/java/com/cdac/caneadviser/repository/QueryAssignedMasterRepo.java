package com.cdac.caneadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.caneadviser.entity.QueryAssignedMaster;
@Repository
public interface QueryAssignedMasterRepo extends JpaRepository<QueryAssignedMaster, Integer> {
   
}
