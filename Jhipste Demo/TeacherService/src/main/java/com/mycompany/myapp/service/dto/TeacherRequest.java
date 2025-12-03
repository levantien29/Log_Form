package com.mycompany.myapp.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequest {

    @NotBlank(message = "Name khong duoc de trong")
    private String teacherName;

    @NotBlank(message = "email khong duoc de trong")
    @Email(message = "Email khong dung dinh dang")
    private String email;

    @NotBlank(message = "SDT khong duoc de trong")
    private String phone;

    @NotBlank(message = "Mon hoc khong duoc de trong")
    private String department;

}
