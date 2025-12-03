package com.example.Product_Mapstruct.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductRequest {
    @NotBlank(message = "Tên không được để trống")
    private String name;

    @NotBlank(message = "Thương hiệu không được để trống")
    private String brand;

    @NotBlank(message = "Mô tả không được để trống")
    private String description; // mô tả

    @NotNull(message = "Giá không được để trống")
    @Min(value = 1, message = "Giá không được nhỏ hơn 1")
    private double price;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 1, message = "Số lượng không được nhỏ hơn 1")
    private int quantity;

    @NotNull(message = "Đánh dấu nổi bật không được để trống")
    private boolean featured;
}
