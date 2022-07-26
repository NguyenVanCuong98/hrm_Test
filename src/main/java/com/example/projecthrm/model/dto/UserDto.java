package com.example.projecthrm.model.dto;

import com.example.projecthrm.model.entity.Account;
import com.example.projecthrm.model.entity.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;


    private String name;

    private Status status;

    private String nameHr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNameHr() {
        return nameHr;
    }

    public void setNameHr(String nameHr) {
        this.nameHr = nameHr;
    }
}
