package com.cdac.caneadviser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.cdac.caneadviser.entity.Queryhandler;
import com.cdac.caneadviser.service.CaneAdviserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/queries")
public class QueryhandlerController {

    @Autowired
    private CaneAdviserService caneAdviserService;

    @GetMapping("/all")
    public List<Queryhandler> getAllQueries() {
        return caneAdviserService.getAllQueries();
    }

    @GetMapping("/answered")
    public List<Queryhandler> getAnsweredQueries() {
        return caneAdviserService.getAnsweredQueries();
    }

    @GetMapping("/unanswered")
    public List<Queryhandler> getUnansweredQueries() {
        return caneAdviserService.getUnansweredQueries();
    }

    @GetMapping("/answered/{expertId}")
    public List<Queryhandler> getAnsweredAssignedQueries(@PathVariable int expertId) {
        return caneAdviserService.getAnsweredAssignedQueries(expertId);
    }

    @GetMapping("/unanswered/{expertId}")
    public List<Queryhandler> getUnansweredAssignedQueries(@PathVariable int expertId) {
        return caneAdviserService.getUnansweredAssignedQueries(expertId);
    }

    @GetMapping("/farmer/{farmId}")
    public List<Queryhandler> getQueriesByFarmerId(@PathVariable int farmId) {
        return caneAdviserService.getQueriesByFarmerId(farmId);
    }
    
    @GetMapping("/pagingAndSortingQueries/{pageNumber}/{pageSize}")
    public Page<Queryhandler> queriesPagination(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize) {
        Sort sort = Sort.by(
            Sort.Order.desc("askedDate") 
        );
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return caneAdviserService.getQueriesPagination(pageable);
    }
    
    @GetMapping("/monthlyCounts")
    public List<Object[]> getMonthlyCountsForCurrentYear() {
        return caneAdviserService.getMonthlyCountsForCurrentYear();
    }






    
    
}
