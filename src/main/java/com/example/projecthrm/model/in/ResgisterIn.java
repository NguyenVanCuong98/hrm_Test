package com.example.projecthrm.model.in;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResgisterIn {
   // @NotEmpty(message = "Thiếu username")
    private String userName;
   // @NotEmpty(message = "Thiếu password")
   // @Min(value = 8, message = "Password phải từ 8 kí tự trở lên")
    private String passWord;
   // @NotEmpty(message = "Thiếu email")
    //@NotEmpty(message = "Email không hợp lệ")
    private String email;
}
