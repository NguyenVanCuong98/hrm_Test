package com.example.projecthrm.service;

import com.example.projecthrm.model.bo.ResponPage;
import com.example.projecthrm.model.bo.Responsess;
import com.example.projecthrm.model.bo.SystemResponse;
import com.example.projecthrm.model.dto.AccountDto;
import com.example.projecthrm.model.dto.RequestAccount;
import com.example.projecthrm.model.entity.Account;
import com.example.projecthrm.model.in.LoginIn;
import com.example.projecthrm.model.in.ResgisterIn;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

import javax.xml.ws.soap.AddressingFeature;
import java.util.Map;
@Service
public interface AccountService extends UserDetailsService {
    Map<String,Object> resgister(ResgisterIn resgisterIn);
    ResponseEntity<SystemResponse<Object>> login(LoginIn loginIn);
    UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException;

    ResponPage getAll(Integer activePage,Integer limit);

}
