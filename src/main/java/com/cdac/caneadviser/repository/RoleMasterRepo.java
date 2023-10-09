package com.cdac.caneadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.caneadviser.entity.RoleMaster;

@Repository
public interface RoleMasterRepo extends JpaRepository<RoleMaster, Integer> {
   
}
