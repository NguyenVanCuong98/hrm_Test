package com.example.projecthrm.service.mapper;

import com.example.projecthrm.model.dto.ResponsePermission;
import com.example.projecthrm.model.entity.Permission;
import com.example.projecthrm.model.in.RequestAddPermission;

public class MapperPermission {
    public  static Permission mapRequestPermission(RequestAddPermission requestPermission){
        Permission permission = new Permission(requestPermission.getDescription());
        return  permission;
    }
    public static ResponsePermission mapEntityPermission ( Permission permission){
        ResponsePermission dtoPermission =  new ResponsePermission(permission.getId(), permission.getName(), permission.getDescription());
        return dtoPermission;
    }
}
