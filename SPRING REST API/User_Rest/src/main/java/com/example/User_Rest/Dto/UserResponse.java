package com.example.User_Rest.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserResponse {
    private Long id;
    private String name;
    private int age;
    private String address;
    private String email;
}
