package com.example.projecthrm.service.mapper;

import com.example.projecthrm.model.dto.RoleDto;
import com.example.projecthrm.model.entity.Role;
import com.example.projecthrm.model.in.RoleIn;

public class MapperRole {
    public static RoleDto map(Role role){
        RoleDto roleDto = new RoleDto();
        roleDto.setIdRole(role.getId());
        roleDto.setName(role.getName());
        roleDto.setListPermission(role.getListPermission());
        return roleDto;
    }

    public static Role map(RoleIn roleIn){
        Role role = new Role();
        role.setId(roleIn.getIdRole());
        role.setName(roleIn.getName());
        return role;
    }
}
