package com.example.Car_Map.Dto;

import lombok.Data;

@Data
public class CarSearchRequest {
    private String name;
    private String code;
    private String model;
    private String brand;
    private String color;
    private Double min;
    private Double max;
}
