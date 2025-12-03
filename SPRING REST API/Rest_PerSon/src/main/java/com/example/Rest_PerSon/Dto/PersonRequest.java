package com.example.Rest_PerSon.Dto;

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
public class PersonRequest {
    @NotBlank(message = "Tên không được để trống")
    private String name;

    @NotNull(message = "Tuổi không được để trống")
    @Min(value = 1, message = "Tuổi phải lớn hơn 1")
    private Integer age;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;

    @Pattern(
    regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{6,}$",
    message = "Mật khẩu phải chứa ít nhất 1 số và 1 ký tự đặc biệt"
    )
    @Size(min = 6, message = "Mật khẩu phải ít nhất 6 kí tự")
    private String password;
}
