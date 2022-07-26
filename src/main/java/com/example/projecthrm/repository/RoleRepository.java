package com.example.projecthrm.repository;

import com.example.projecthrm.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String name);
    Role findRoleById(Integer id);
}
