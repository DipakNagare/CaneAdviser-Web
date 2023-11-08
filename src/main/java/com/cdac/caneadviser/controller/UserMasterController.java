package com.cdac.caneadviser.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cdac.caneadviser.entity.UserMaster;
import com.cdac.caneadviser.service.CaneAdviserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user-masters")
public class UserMasterController {

	@Autowired
    private  CaneAdviserService caneAdviserService;


	@GetMapping()
    public List<UserMaster> getAllUserMasters() {
        return caneAdviserService.getAllUserMasters();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserMaster> getUserMasterById(@PathVariable String userId) {
        Optional<UserMaster> userMaster = caneAdviserService.getUserMasterById(userId);
        return userMaster.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/create")
    public ResponseEntity<UserMaster> createUser(@RequestBody UserMaster user) {
        UserMaster savedUser = caneAdviserService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
    	caneAdviserService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/")
    public String updateUser(@RequestBody UserMaster user) {
         caneAdviserService.updateUser(user);
        return "Data Updated Successfully";
    }
}
