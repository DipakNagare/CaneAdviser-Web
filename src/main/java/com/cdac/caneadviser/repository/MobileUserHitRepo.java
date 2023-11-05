package com.cdac.caneadviser.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.cdac.caneadviser.entity.MobileUserHit;

@Repository
public interface MobileUserHitRepo extends JpaRepository<MobileUserHit, Integer> {

}
