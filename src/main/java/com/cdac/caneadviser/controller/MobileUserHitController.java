package com.cdac.caneadviser.controller;

import com.cdac.caneadviser.entity.MobileUserHit;
import com.cdac.caneadviser.service.CaneAdviserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mobile-user-hits")
public class MobileUserHitController {

	@Autowired
    private CaneAdviserService caneAdviserService;

   

    @GetMapping
    public List<MobileUserHit> getAllMobileUserHits() {
        return caneAdviserService.getAllMobileUserHits();
    }

    @PostMapping("/")
    public ResponseEntity<MobileUserHit> saveMobileUserHit(@RequestBody MobileUserHit mobileUserHit) {
        MobileUserHit savedMobileUserHit = caneAdviserService.saveMobileUserHit(mobileUserHit);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMobileUserHit);
    }
    @GetMapping("/overallCount")
    public long getOverallCount() {
        return caneAdviserService.getOverallCount();
    }
}
