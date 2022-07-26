package com.example.projecthrm.model.bo;

import com.example.projecthrm.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

public class Respon {
    private String Status ;
    private String messenger;
    private Object data;

    public Respon(String status, Object data) {
        Status = status;
        this.data = data;
    }

    public Respon(String status,String messenger, Object data) {
        Status = status;
        this.data = data;
        this.messenger=messenger;
    }

}
