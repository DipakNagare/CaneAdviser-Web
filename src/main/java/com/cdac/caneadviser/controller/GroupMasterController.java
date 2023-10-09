package com.cdac.caneadviser.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cdac.caneadviser.entity.GroupMaster;
import com.cdac.caneadviser.service.CaneAdviserService;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/group-masters")
public class GroupMasterController {

    private CaneAdviserService caneAdviserService;
    
    public GroupMasterController(CaneAdviserService caneAdviserService) {
        this.caneAdviserService = caneAdviserService;
    }


    @GetMapping
    public ResponseEntity<List<GroupMaster>> getAllGroupMasters() {
        List<GroupMaster> groupMasters = caneAdviserService.getAllGroupMasters();
        return ResponseEntity.ok(groupMasters);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<GroupMaster> getGroupMasterById(@PathVariable int groupId) {
        Optional<GroupMaster> groupMaster = caneAdviserService.getGroupMasterById(groupId);
        if (groupMaster.isPresent()) {
            return ResponseEntity.ok(groupMaster.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<GroupMaster> saveGroupMaster(@RequestBody GroupMaster groupMaster) {
        GroupMaster savedGroupMaster = caneAdviserService.saveGroupMaster(groupMaster);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGroupMaster);
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteGroupMaster(@PathVariable int groupId) {
    	caneAdviserService.deleteGroupMaster(groupId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<GroupMaster> updateGroupMaster(@PathVariable int groupId, @RequestBody GroupMaster updatedGroupMaster) {
        GroupMaster updated = caneAdviserService.updateGroupMaster(groupId, updatedGroupMaster);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
