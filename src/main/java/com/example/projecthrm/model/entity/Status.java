package com.example.projecthrm.model.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "status")
public class Status extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @Column(unique = true)
    private String thutu;

    public Status() {
    }

    public Status(Long id, String name, String thutu) {
        this.id = id;
        this.name = name;
        this.thutu = thutu;
    }

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

    public String getThutu() {
        return thutu;
    }

    public void setThutu(String thutu) {
        this.thutu = thutu;
    }
}
