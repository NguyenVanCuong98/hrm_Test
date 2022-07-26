package com.example.projecthrm.repository;

import com.example.projecthrm.model.entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryPermission extends JpaRepository<Permission,Integer> {
    List<Permission> findAll();
    Permission findEntityPermissionByName(String name);
    Permission findEntityPermissionById(Integer id);
    Page<Permission> findByNameContaining(String text, Pageable pageable);
}
