package com.example.projecthrm.service.imp;

import com.example.projecthrm.model.bo.Responsess;
import com.example.projecthrm.model.dto.ResponsePermission;
import com.example.projecthrm.model.entity.Permission;
import com.example.projecthrm.model.in.RequestAddPermission;
import com.example.projecthrm.model.in.RequestUpdatePermission;
import com.example.projecthrm.repository.RepositoryPermission;
import com.example.projecthrm.service.ServicePermission;
import com.example.projecthrm.service.mapper.MapperPermission;
import com.example.projecthrm.utils.ResponseString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServicesPermissionImp implements ServicePermission {
    @Autowired
    private RepositoryPermission reponsitoryPermission;

    @Override
    public Responsess getAllPermission() {
        List<ResponsePermission> productDtos = reponsitoryPermission.findAll().stream().map(MapperPermission::mapEntityPermission).collect(Collectors.toList());
        if (productDtos.isEmpty()){
            return new Responsess(false, ResponseString.WRONG_LIST);
        }else {
            return new Responsess(true, ResponseString.SUCCESS, productDtos);
        }
    }

    @Override
    public Responsess searchPaginationPermission(String text, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<Permission> listPage = reponsitoryPermission.findByNameContaining(text, pageable);
        List<ResponsePermission> productDtos = listPage.stream().map(MapperPermission::mapEntityPermission).collect(Collectors.toList());
        if (productDtos.isEmpty()){
            return new Responsess(false, ResponseString.WRONG_LIST);
        }else {
            return new Responsess(true, ResponseString.SUCCESS, productDtos, listPage.getTotalPages());
        }
    }

    @Override
    public Responsess addPermission(RequestAddPermission requestPermission) {
        Permission entityPermission = MapperPermission.mapRequestPermission(requestPermission);
        Permission getPermission = reponsitoryPermission.findEntityPermissionByName(requestPermission.getName());
        if (getPermission != null)return new Responsess(false, ResponseString.WRONG_NAME);
        entityPermission.setName(requestPermission.getName());
        reponsitoryPermission.save(entityPermission);
        return new Responsess(true,ResponseString.SUCCESS,entityPermission);
    }

    @Override
    public Responsess updatePermission(RequestUpdatePermission requestUpdatePermission, Integer id) {
        Permission entityPermission = reponsitoryPermission.findEntityPermissionById(id);
        if (entityPermission == null)return  new Responsess(false,ResponseString.WRONG_ID);
        entityPermission.setDescription(requestUpdatePermission.getDescription());
        reponsitoryPermission.save(entityPermission);
        return new Responsess(true,ResponseString.SUCCESS,entityPermission);
    }

    @Override
    public Responsess deletePermission(Integer id) {
        Permission entityPermission = reponsitoryPermission.findEntityPermissionById(id);
        if (entityPermission == null)return  new Responsess(false,ResponseString.WRONG_ID);
        reponsitoryPermission.deleteById(id);
        return new Responsess(true,ResponseString.SUCCESS,entityPermission);
    }
}
