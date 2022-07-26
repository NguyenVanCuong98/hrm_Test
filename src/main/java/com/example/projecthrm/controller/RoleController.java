package com.example.projecthrm.controller;

import com.example.projecthrm.model.entity.Role;
import com.example.projecthrm.model.in.ResgisterIn;
import com.example.projecthrm.model.in.RoleIn;
import com.example.projecthrm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/role")
@RestController
@CrossOrigin("*")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping(value = "")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(roleService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?>create(@RequestBody RoleIn roleIn){
        //return new ResponseEntity<Role>(roleService.create(roleIn), HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.OK).body(roleService.create(roleIn));
    }
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?>update(@PathVariable Integer id,@RequestBody RoleIn roleIn){
       // return new ResponseEntity<Role>(roleService.update(id,roleIn), HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(roleService.update(id,roleIn));
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?>delete(@PathVariable Integer id){
        //return new ResponseEntity<Map<String, Object>>(roleService.delete(id), HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(roleService.delete(id));
    }
}
