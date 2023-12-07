package com.cdac.caneadviser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cdac.caneadviser.entity.Queryhandler;
import com.cdac.caneadviser.service.CaneAdviserService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/queries")
public class QueryhandlerController {

    @Autowired
    private CaneAdviserService caneAdviserService;

    @PostMapping("/submit-answer")
    public ResponseEntity<String> submitAnswer(@RequestParam(name = "queId") int queId,
            @RequestParam(name = "answer") String answer) {
        try {
            if (answer != null) {
                caneAdviserService.updateQueryAnswer(queId, answer);
                return new ResponseEntity<>("Answer Submitted Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Answer not provided", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error Submitting Answer: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-query/{queId}")
    public ResponseEntity<String> deleteQuery(@PathVariable int queId) {
        try {
            caneAdviserService.deleteQuery(queId);
            return new ResponseEntity<>("Query deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting query: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/queryHandler")
    public String queryHandler(@RequestBody Queryhandler queryHandler) throws IOException {
        return caneAdviserService.queryHandler(queryHandler);
    }

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
                Sort.Order.desc("askedDate"));
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return caneAdviserService.getQueriesPagination(pageable);
    }

    @GetMapping("/queriesYearMonthWise")
    public List<Object[]> getMonthlyCountsForCurrentYear() {
        return caneAdviserService.getMonthlyCountsForCurrentYear();
    }

}
