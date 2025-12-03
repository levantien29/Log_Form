package com.example.Order_Management.Dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderRequest {
    @NotBlank(message = "Tên không được để trống")
    private String name;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotNull(message = "Tổng tiền không được để trống")
    @DecimalMin(value = "1.0", message = "Tổng tiền không được nhỏ hơn 1")
    private Double total;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 1, message = "Số lượng không được nhỏ hơn 1")
    private Integer quantity;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&^()_+\\-=])[A-Za-z\\d@$!%*#?&^()_+\\-=]{8,}$", message = "Mật khẩu phải chứa ít nhất 8 ký tự, bao gồm chữ, số và ký tự đặc biệt")
    private String password;
}
