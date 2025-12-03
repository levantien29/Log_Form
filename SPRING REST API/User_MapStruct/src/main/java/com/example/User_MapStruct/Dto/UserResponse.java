package com.example.User_MapStruct.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private int age;
    private String gender;
    private String address;
    private String email;
}
