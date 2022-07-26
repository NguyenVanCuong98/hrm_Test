package com.example.projecthrm.model.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleIn {
        private Integer idRole;
        private String name;
        private Set<Integer> listIdPermission;
}
