package com.example.User_Dto.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserReponse {
    private Long id;
    private String name;
    private int age;
}
