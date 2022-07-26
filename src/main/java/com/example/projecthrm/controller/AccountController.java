package com.example.projecthrm.controller;

import com.example.projecthrm.exception.ResourceNotFoundException;
import com.example.projecthrm.model.bo.ResponPage;
import com.example.projecthrm.model.bo.SystemResponse;
import com.example.projecthrm.model.entity.Account;
import com.example.projecthrm.model.entity.Status;
import com.example.projecthrm.model.in.LoginIn;
import com.example.projecthrm.model.in.ResgisterIn;
import com.example.projecthrm.repository.AccountRepository;
import com.example.projecthrm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api")
@RestController
@CrossOrigin("*")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;
    @PostMapping(value = "/register")
    public ResponseEntity<?> resgister(@RequestBody ResgisterIn resgisterIn){
        if(accountRepository.existsAccountByEmail(resgisterIn.getEmail())){
            return new ResponseEntity<>("email is already taken!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(accountService.resgister(resgisterIn), HttpStatus.OK);
    }
    @PostMapping(value = "/login")
    public ResponseEntity<SystemResponse<Object>> login(@RequestBody LoginIn loginIn){
        return accountService.login(loginIn);
    }
    @GetMapping(value = "/account")
    public ResponseEntity<?> getAll( @RequestParam Integer activePage
            , @RequestParam Integer limit){
        return new ResponseEntity<ResponPage>(accountService.getAll(activePage,limit), HttpStatus.OK);
    }

    @PutMapping("/account/{accountId}")
    public Account updateAccount(@PathVariable Integer accountId, @RequestBody Account accountRequest) {
        return accountRepository.findById(accountId).map(account -> {
//            account.setId(accountRequest.getId());
            account.setUserName(accountRequest.getUserName());
            account.setEmail(accountRequest.getEmail());
            return accountRepository.save(account);
        }).orElseThrow(() -> new ResourceNotFoundException("accountId " +accountId + " not found"));
    }
    @GetMapping(value = "/test")
    public ResponseEntity<?> test(){
        return new ResponseEntity<>("tesst thanh cong", HttpStatus.OK);
    }

}

