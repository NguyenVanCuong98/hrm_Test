package com.example.projecthrm.service.imp;

import com.example.projecthrm.jwt.JwtUtility;
import com.example.projecthrm.model.bo.ResponPage;
import com.example.projecthrm.model.bo.Response;
import com.example.projecthrm.model.bo.SystemResponse;
import com.example.projecthrm.model.dto.AccountDto;
import com.example.projecthrm.model.dto.UserDto;
import com.example.projecthrm.model.entity.Account;
import com.example.projecthrm.model.entity.Role;
import com.example.projecthrm.model.entity.User;
import com.example.projecthrm.model.in.LoginIn;
import com.example.projecthrm.model.in.ResgisterIn;
import com.example.projecthrm.repository.AccountRepository;
import com.example.projecthrm.repository.RoleRepository;
import com.example.projecthrm.repository.UserRepository;
import com.example.projecthrm.service.AccountService;
import com.example.projecthrm.service.mapper.MapAccount;
import com.example.projecthrm.service.mapper.UserMapper;
import com.example.projecthrm.utils.StringResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtility jwtUtility;

    @Override
    public Map<String,Object> resgister(ResgisterIn resgisterIn)  {
        Account account = MapAccount.map(resgisterIn);
        Role role = roleRepository.findByName("hr");
        account.setRoles(Collections.singleton(role));
        accountRepository.save(account);
        Map<String,Object> result = new HashMap<>();
        String messenger = "Đăng ký tài khoản thành công";
        result.put("result", messenger);
        return result ;
    }
    @Override
    public ResponseEntity<SystemResponse<Object>> login(LoginIn loginIn) {
        Account account = accountRepository.findAccountByEmail(loginIn.getEmail());

        System.out.println("accounthhhhhh" +account);
       User check= userRepository.findUserByAccount(account);
        System.out.println("check" +check);
        if (check == null){
            List<User> user = userRepository.findUserOne();
            user.get(0).setAccount(account);
            userRepository.save(user.get(0));
        }

//        if(account == null)
//        {
//            return Response.badRequest(StringResponse.Account_Not_Found);
//        }

        if(BCrypt.checkpw(loginIn.getPassWord(),account.getPassWord()) == false) {
            return Response.badRequest(StringResponse.Pass_Wrong);
        }
        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginIn.getEmail(),loginIn.getPassWord()));
        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Trả về jwt cho người dùng
        String jwt = jwtUtility.generateJwtToken(loginIn.getEmail());
        return Response.ok(jwt);
    }

    @Override
    public UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException {
        Account user = accountRepository.findAccountByEmail(var1);
        if(user == null)
        {
            throw new UsernameNotFoundException("User " + var1 + " was not found in the database");
        }
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        List<Role> userRole = (List<Role>) user.getRoles();
        for (int i = 0;i<userRole.size();i++)
        {
            System.out.printf(""+userRole.get(i).getName());
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+userRole.get(i).getName());
            grantedAuthorityList.add(grantedAuthority);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassWord(),grantedAuthorityList);
    }

    @Override
    public ResponPage getAll(Integer activePage, Integer limit) {
        Pageable pageable = PageRequest.of(activePage,limit);
        Page<Account> pageResult = accountRepository.findAll(pageable);
        List<AccountDto> listAccount= pageResult.stream().map(MapAccount::map).collect(Collectors.toList());
        return  new ResponPage(true,"done",pageResult.getNumber(),pageResult.getTotalPages(),listAccount);
    }
}
