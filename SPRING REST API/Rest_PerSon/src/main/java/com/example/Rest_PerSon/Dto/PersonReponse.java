package com.example.Rest_PerSon.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonReponse {
    private Long id;
    private String name;
    private int age;
    private String address;
}
