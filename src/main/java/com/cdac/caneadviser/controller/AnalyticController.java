package com.cdac.caneadviser.controller;

import com.cdac.caneadviser.entity.Analytic;
import com.cdac.caneadviser.service.CaneAdviserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analytics")
public class AnalyticController {

    @Autowired
    private CaneAdviserService caneAdviserService;

    @GetMapping("/all")
    public List<Analytic> getAllAnalytics() {
        return caneAdviserService.getAllAnalytics();
    }

    @PostMapping("/save")
    public ResponseEntity<Analytic> saveAnalytic(@RequestBody Analytic analytic) {
        // System.out.println("++++_____++++ = " + analytic.getAccContent());
        // System.out.println("++++_____++++ = " + analytic.getAccDate());
        // System.out.println("++++_____++++ = " + analytic.getAccSubtop());
        // System.out.println("++++_____++++ = " + analytic.getAccSubtop());
        // System.out.println("++++_____++++ = " + analytic.getNetworkType());

        Analytic savedAnalytic = caneAdviserService.saveAnalytic(analytic);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnalytic);
        
    }

    @GetMapping("/technologyWiseCount")
    public List<Object[]> getTechnologyWiseCount() {
        return caneAdviserService.getTechnologyWiseCount();
    }
}
