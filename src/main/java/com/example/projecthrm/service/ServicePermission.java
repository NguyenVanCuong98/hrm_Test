package com.example.projecthrm.service;

import com.example.projecthrm.model.bo.Responsess;
import com.example.projecthrm.model.in.RequestAddPermission;
import com.example.projecthrm.model.in.RequestUpdatePermission;
import org.springframework.stereotype.Service;

@Service
public interface ServicePermission {
    Responsess getAllPermission();
    Responsess searchPaginationPermission(String text,Integer page , Integer limit);
    Responsess addPermission(RequestAddPermission requestPermission);
    Responsess updatePermission(RequestUpdatePermission requestUpdatePermission, Integer id);
    Responsess deletePermission(Integer id);
}
