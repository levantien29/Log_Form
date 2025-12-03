package com.example.Car_Map.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CarRequest {
    @NotBlank(message = "Tên không được để trống")
    private String name;

    @NotBlank(message = "Mã xe không được để trống")
    private String code;

    @NotBlank(message = "Dòng xe không được để trống")
    private String model;

    @NotBlank(message = "Hãng xe không được để trống")
    private String brand;

    @NotBlank(message = "Vui lòng nhập màu xe")
    private String color;

    @NotNull(message = "Giá xe không được để trống")
    @Min(value = 1, message = "Vui lòng nhập giá xe hợp lệ")
    private Double price;

    @NotNull(message = "Vui lòng nhập năm sản xuất")
    @Min(value = 1, message = "Vui lòng nhập năm hợp lệ")
    private Integer year;
}
