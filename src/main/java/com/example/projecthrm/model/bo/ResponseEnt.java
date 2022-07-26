package com.example.projecthrm.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEnt {
    private String message;
    private Object dataEmployee;
    private Object dataStatus;

    public ResponseEnt(String message) {
        this.message = message;
    }
}
