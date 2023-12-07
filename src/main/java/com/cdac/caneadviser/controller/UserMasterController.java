package com.cdac.caneadviser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cdac.caneadviser.entity.QueryAssignedMaster;
import com.cdac.caneadviser.entity.Queryhandler;
import com.cdac.caneadviser.entity.RoleMaster;
import com.cdac.caneadviser.entity.UserMaster;
import com.cdac.caneadviser.service.CaneAdviserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user-masters")
public class UserMasterController {

    @Autowired
    private CaneAdviserService caneAdviserService;

    @PostMapping("/assign-query")
    public ResponseEntity<String> assignQuery(@RequestBody Map<String, Object> payload) {
        // Extract userId and questionId from the payload map
        String userId = (String) payload.get("userId");
        int questionId = (int) payload.get("questionId");
        {
            {
                try {
                    // Retrieve UserMaster and Queryhandler entities
                    Optional<UserMaster> userMaster = caneAdviserService.getUserMasterById(userId);
                    Optional<Queryhandler> queryhandler = caneAdviserService.getQueryhandlerById(questionId);

                    // Check if both entities are present
                    if (userMaster.isPresent() && queryhandler.isPresent()) {
                        // Create and save QueryAssignedMaster entity
                        QueryAssignedMaster assignedMaster = new QueryAssignedMaster();
                        assignedMaster.setStatus("Unanswered"); // Set the default status or customize it as needed
                        assignedMaster.setUserMaster(userMaster.get());
                        assignedMaster.setQueryhandler(queryhandler.get());
                        caneAdviserService.saveQueryAssignedMaster(assignedMaster);

                        return new ResponseEntity<>("Query Assigned Successfully", HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("User or Queryhandler not found", HttpStatus.NOT_FOUND);
                    }
                } catch (Exception e) {
                    return new ResponseEntity<>("Error Assigning Query: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
    }

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
