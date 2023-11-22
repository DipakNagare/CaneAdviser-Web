package com.cdac.caneadviser.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cdac.caneadviser.entity.RoleMaster;
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
    public String deleteUser(@PathVariable String userId) {
    	caneAdviserService.deleteUser(userId);
        return "Data Delete Successfully";
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId, @RequestBody UserMaster updatedUser) {
        try {
            // Set the user ID in the updated data
            updatedUser.setUserId(userId);

            // Directly set the roleId in the updated data
            RoleMaster roleMaster = new RoleMaster();
            roleMaster.setRoleId(2); // TODO - static value of expert role
            updatedUser.setRoleMaster(roleMaster);

            // Update the user
            caneAdviserService.updateUser(updatedUser);

            return new ResponseEntity<>("Data Updated Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error Updating Data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
