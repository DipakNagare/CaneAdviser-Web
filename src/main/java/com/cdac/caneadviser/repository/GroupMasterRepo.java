package com.cdac.caneadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.caneadviser.entity.GroupMaster;

@Repository
public interface GroupMasterRepo extends JpaRepository<GroupMaster, Integer> {
   
}
