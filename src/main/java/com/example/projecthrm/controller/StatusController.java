package com.example.projecthrm.controller;

import com.example.projecthrm.exception.ResourceNotFoundException;
import com.example.projecthrm.model.entity.Status;
import com.example.projecthrm.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class StatusController {
    @Autowired
    private StatusRepository statusRepository;

    @GetMapping("/status")
    public Page<Status> getAllStatus(Pageable pageable) {
        return statusRepository.findAll(pageable);
    }

    @PostMapping("/status")
    public Status createStatus( @RequestBody Status status) {
        return statusRepository.save(status);
    }

    @PutMapping("/status/{statusId}")
    public Status updateStatus(@PathVariable Long statusId,  @RequestBody Status statusRequest) {
        return statusRepository.findById(statusId).map(status -> {
            status.setName(statusRequest.getName());
            status.setThutu(statusRequest.getThutu());
            return statusRepository.save(status);
        }).orElseThrow(() -> new ResourceNotFoundException("statusId " + statusId + " not found"));
    }


    @DeleteMapping("/status/{statusId}")
    public ResponseEntity<?> deleteStatus(@PathVariable Long statusId) {
        return statusRepository.findById(statusId).map(status -> {
            statusRepository.delete(status);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("statusId " + statusId + " not found"));
    }
}

