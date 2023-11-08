package com.cdac.caneadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.caneadviser.entity.UserMaster;

@Repository
public interface UserMasterRepo extends JpaRepository<UserMaster, String> {
}
