package com.example.projecthrm.service.imp;

import com.example.projecthrm.model.bo.Respon;
import com.example.projecthrm.model.bo.Responsess;
import com.example.projecthrm.model.dto.AccountDto;
import com.example.projecthrm.model.dto.RoleDto;
import com.example.projecthrm.model.entity.Permission;
import com.example.projecthrm.model.entity.Role;
import com.example.projecthrm.model.in.RoleIn;
import com.example.projecthrm.repository.RepositoryPermission;
import com.example.projecthrm.repository.RoleRepository;
import com.example.projecthrm.service.RoleService;
import com.example.projecthrm.service.mapper.MapAccount;
import com.example.projecthrm.service.mapper.MapperRole;
import com.example.projecthrm.utils.ResponseString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RepositoryPermission reponsitoryPermission;

    @Override
    public Respon getAll() {
        List<Role> list = roleRepository.findAll();
        List<RoleDto> listAccount= list.stream().map(MapperRole::map).collect(Collectors.toList());
        return new Respon("true","ok",listAccount);
    }

    @Override
    public Responsess create(RoleIn roleIn) {
        Role role = MapperRole.map(roleIn);
        if (roleIn.getListIdPermission() !=null){
            for (Integer id:roleIn.getListIdPermission()) {
                Permission entityPermission = reponsitoryPermission.findEntityPermissionById(id);
                role.getListPermission().add(entityPermission);
            }
        }
        roleRepository.save(role);
        return new Responsess(true, ResponseString.SUCCESS, role);
    }

    @Override
    public Responsess update(Integer id, RoleIn roleIn) {
        Role role = roleRepository.findRoleById(id);
        role.setId(id);
        role.setName(roleIn.getName());
        if (roleIn.getListIdPermission() !=null){
            Set<Permission> listNew = new HashSet<>() ;
            for (Integer idPermission:roleIn.getListIdPermission()) {
                Permission entityPermission = reponsitoryPermission.findEntityPermissionById(idPermission);
                if (entityPermission != null) listNew.add(entityPermission);
            }
            role.setListPermission(listNew);
        }
        roleRepository.save(role);
        return new Responsess(true, ResponseString.SUCCESS, role);
    }

    @Override
    public Responsess delete(Integer id) {
        Role role = roleRepository.findRoleById(id);
        if (role == null)return new Responsess(false,ResponseString.WRONG_ID);
        roleRepository.deleteById(id);
        return new Responsess(true, ResponseString.SUCCESS, role);
    }


}
