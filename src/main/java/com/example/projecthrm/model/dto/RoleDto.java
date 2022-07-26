package com.example.projecthrm.model.dto;


import com.example.projecthrm.model.entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private Integer idRole;
    private String name;
    private Set<Permission> listPermission = new HashSet<>();
}
