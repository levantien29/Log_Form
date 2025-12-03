package com.example.Phone_MapStruct.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhoneResponse {
    private Long id;
    private String name;
    private String brand;
    private double price;
    private int quantity;
    private boolean featured;
}
