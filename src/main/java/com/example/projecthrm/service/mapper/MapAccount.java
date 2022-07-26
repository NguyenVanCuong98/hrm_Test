package com.example.projecthrm.service.mapper;

import com.example.projecthrm.model.dto.AccountDto;
import com.example.projecthrm.model.entity.Account;
import com.example.projecthrm.model.in.ResgisterIn;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class MapAccount {
    public static Account map(ResgisterIn resgisterIn)
    {
        Account accounts = new Account();
        accounts.setEmail(resgisterIn.getEmail());
        accounts.setUserName(resgisterIn.getUserName());
        String hash = BCrypt.hashpw(resgisterIn.getPassWord(),BCrypt.gensalt(10));
        accounts.setPassWord(hash);
        return accounts;
    }

    public static AccountDto map(Account account){
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setUserName(account.getUserName());
        accountDto.setEmail(account.getEmail());
        return accountDto;
    }
}
