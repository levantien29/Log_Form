package com.example.Animal_Rest.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalRequest {

    @NotBlank(message = "Tên không được để trống")
    private String name;

    @NotBlank(message = "Loài không được để trống")
    private String species; // loài

    @NotBlank(message = "Màu sắc không được để trống")
    private String color;

    @NotNull(message = "Tuổi không được để trống")
    @Min(value = 1, message = "Tuổi phải lớn hơn 1")
    private Integer age;

    @NotBlank(message = "Giới tính không được để trống")
    private String gender;

    @NotBlank(message = "Mật Khẩu không được để trống")
    @Size(min = 6, message = "Mật khẩu phải ít nhất 6 kí tự")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{6,}$", message = "Mật khẩu phải chứa ít nhất 1 số và 1 ký tự đặc biệt")
    private String password;

}
