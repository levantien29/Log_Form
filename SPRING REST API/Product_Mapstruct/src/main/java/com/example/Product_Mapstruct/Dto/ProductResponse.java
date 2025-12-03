package com.example.Product_Mapstruct.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String brand;
    private String description; // mô tả
    private double price;
    private int quantity;
    private boolean featured;
}
