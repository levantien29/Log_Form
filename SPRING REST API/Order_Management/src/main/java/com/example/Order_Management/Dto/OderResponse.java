package com.example.Order_Management.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OderResponse {
    private Long id;
    private String name;
    private String email;
    private double total;
    private int quantity;
}
