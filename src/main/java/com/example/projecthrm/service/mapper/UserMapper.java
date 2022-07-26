package com.example.projecthrm.service.mapper;

import com.example.projecthrm.model.dto.UserDto;
import com.example.projecthrm.model.entity.User;

public class UserMapper {
    public static UserDto map(User user){
        UserDto userDto = new UserDto();
       userDto.setId(user.getId());
       userDto.setName(user.getName());
       userDto.setStatus(user.getStatus());
        System.out.println("user"+ user);
       if(user.getAccount() !=null) {
           userDto.setNameHr(user.getAccount().getUserName());
       }
       return userDto;
    }
}
