package com.example.Product_Rest.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductResponse {
    private Long id;
    private String name;
    private double price;
    private int quantity;
}
