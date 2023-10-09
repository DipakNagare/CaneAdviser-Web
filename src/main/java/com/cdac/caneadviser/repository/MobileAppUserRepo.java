package com.cdac.caneadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.caneadviser.entity.MobileAppUser;

@Repository
public interface MobileAppUserRepo extends JpaRepository<MobileAppUser, String> {
   
}
