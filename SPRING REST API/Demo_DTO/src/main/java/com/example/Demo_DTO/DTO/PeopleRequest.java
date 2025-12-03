package com.example.Demo_DTO.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeopleRequest {
    private String name;
    private String address;
    private String email;
    private String password;
}
