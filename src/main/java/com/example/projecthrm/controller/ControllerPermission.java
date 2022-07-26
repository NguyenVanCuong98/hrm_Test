package com.example.projecthrm.controller;

import com.example.projecthrm.model.in.RequestAddPermission;
import com.example.projecthrm.model.in.RequestUpdatePermission;
import com.example.projecthrm.service.ServicePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/permission")
public class ControllerPermission {
    @Autowired
    private ServicePermission servicePermission;
    @GetMapping("/get")
    ResponseEntity<?> getAllPermission(){
        return ResponseEntity.status(HttpStatus.OK).body(servicePermission.getAllPermission());
    }
    @PostMapping("/add")
    ResponseEntity<?>addPermission(@RequestBody RequestAddPermission requestPermission){
        return ResponseEntity.status(HttpStatus.OK).body(servicePermission.addPermission(requestPermission));
    }
    @PutMapping("/update/{id}")
    ResponseEntity<?>updatePermission(@RequestBody RequestUpdatePermission requestUpdatePermission,
                                      @PathVariable Integer id
    ){
        return ResponseEntity.status(HttpStatus.OK).body(servicePermission.updatePermission(requestUpdatePermission ,id));
    }
    @DeleteMapping ("/delete/{id}")
    ResponseEntity<?>deletePermission(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(servicePermission.deletePermission(id));
    }
    @GetMapping()
    ResponseEntity<?>searchPagination(@RequestParam(name = "textSearch",defaultValue = "")String text,
                                      @RequestParam(name = "activePage",defaultValue = "0")Integer page,
                                      @RequestParam(name = "limit",defaultValue = "2")Integer limit){
        return ResponseEntity.status(HttpStatus.OK).body(servicePermission.searchPaginationPermission(text,page,limit));
    }
}

