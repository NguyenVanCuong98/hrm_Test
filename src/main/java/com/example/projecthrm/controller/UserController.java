package com.example.projecthrm.controller;

import com.example.projecthrm.exception.ResourceNotFoundException;
import com.example.projecthrm.model.bo.Respon;
import com.example.projecthrm.model.bo.ResponPage;
import com.example.projecthrm.model.dto.UserDto;
import com.example.projecthrm.model.entity.Account;
import com.example.projecthrm.model.entity.Status;
import com.example.projecthrm.model.entity.User;
import com.example.projecthrm.repository.AccountRepository;
import com.example.projecthrm.repository.StatusRepository;
import com.example.projecthrm.repository.UserRepository;
import com.example.projecthrm.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin( "*")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/status/{statusId}/users")
    public Page<User> getAllUserByStatusId(@PathVariable(value = "statusId") Long statusId,
                                           Pageable pageable) {
        return userRepository.findByStatusId(statusId, pageable);
    }
    @GetMapping("/users")
    public ResponPage getAllUser(Integer activePage, Integer limit){
        Sort sort = Sort.by("status_id","account_id").ascending();
      Pageable pageable = PageRequest.of(activePage,limit,sort);
      Page<User>pageResult = userRepository.getPage(pageable);
        List<UserDto> listAccount= pageResult.stream().map(UserMapper::map).collect(Collectors.toList());
      return  new ResponPage(true,"done",pageResult.getNumber(),pageResult.getTotalPages(),listAccount);
    }
    @GetMapping("/users/one")
    public Respon getOne() {
        List<User> users = userRepository.findUserOne();
        return new Respon("ok",users);
    }

    @PostMapping("/status/{statusId}/users")
    public User createUser(@PathVariable (value = "statusId") Long statusId,
                           @RequestBody User user ) {
        return statusRepository.findById(statusId).map(status -> {
            user.setStatus(status);

            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("statusId " + statusId + " not found"));
    }

    @PutMapping("/status/{statusId}/users/{usersId}")
    public User updateUser(@PathVariable (value = "statusId") Long statusId,
                           @PathVariable (value = "usersId") Long usersId,
                           @RequestBody User userRequest) {
        if(!statusRepository.existsById(statusId)) {
            throw new ResourceNotFoundException("statusId " + statusId + " not found");
        }

        Status status = statusRepository.getById(statusId);

        return userRepository.findById(usersId).map(user -> {
            user.setName(userRequest.getName());
            user.setStatus(status);
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("usersId " + usersId + "not found"));
    }

    @PutMapping("/status/{statusId}/users/one/{usersId}")
    public User updateOneUser(@PathVariable (value = "statusId") Long statusId,
                              @PathVariable (value = "usersId") Long usersId,
                              @RequestBody User userRequest) {
        if(!statusRepository.existsById(statusId)) {
            throw new ResourceNotFoundException("statusId " + statusId + " not found");
        }

        Status status = statusRepository.getById(statusId);


        return userRepository.findById(usersId).map(user -> {
//            user.setName(userRequest.getName());
            user.setStatus(status);
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("usersId " + usersId + "not found"));
    }

    @DeleteMapping("/status/{statusId}/users/{usersId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "statusId") Long statusId,
                                           @PathVariable (value = "usersId") Long usersId) {
        return userRepository.findByIdAndStatusId(usersId, statusId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + usersId + " and postId " + statusId));
    }
}

