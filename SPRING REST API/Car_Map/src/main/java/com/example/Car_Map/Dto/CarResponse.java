package com.example.Car_Map.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CarResponse {
    private Long id;
    private String name;
    private String code;
    private String model;
    private String brand;
    private String color;
    private double price;
    private int year;
}
