package com.cdac.caneadviser.controller;

import com.cdac.caneadviser.entity.Analytic;
import com.cdac.caneadviser.service.CaneAdviserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/analytics")
public class AnalyticController {

	@Autowired
    private CaneAdviserService caneAdviserService;
   

    @GetMapping
    public List<Analytic> getAllAnalytics() {
        return caneAdviserService.getAllAnalytics();
    }

    @PostMapping("/")
    public ResponseEntity<Analytic> saveAnalytic(@RequestBody Analytic analytic) {
        Analytic savedAnalytic = caneAdviserService.saveAnalytic(analytic);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnalytic);
    }

    @GetMapping("/count/{accContent}")
    public long countByAccContent(@PathVariable String accContent) {
        return caneAdviserService.countByAccContent(accContent);
    }

}
