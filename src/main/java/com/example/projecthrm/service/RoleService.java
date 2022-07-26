package com.example.projecthrm.service;

import com.example.projecthrm.model.bo.Respon;
import com.example.projecthrm.model.bo.Responsess;
import com.example.projecthrm.model.entity.Role;
import com.example.projecthrm.model.in.RoleIn;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service

public interface RoleService{
    Respon getAll();
    Responsess create(RoleIn roleIn);
    Responsess update(Integer id , RoleIn roleIn);
    Responsess delete(Integer id);
}
