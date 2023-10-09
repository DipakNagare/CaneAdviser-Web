package com.cdac.caneadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.caneadviser.entity.UserPasswordReset;
import com.cdac.caneadviser.entity.UserPasswordResetPK;

@Repository
public interface UserPasswordResetPKRepo extends JpaRepository<UserPasswordReset, UserPasswordResetPK> {
    
}
