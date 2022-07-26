package com.example.projecthrm.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Responsess {
    private boolean status;
    private String message;
    private Object data;
    private Integer totalPage;

    public Responsess(boolean status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Responsess(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public Responsess(boolean status) {
        this.status = status;
    }
}
