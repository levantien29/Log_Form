package com.example.Demo_DTO.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeopleRepose {
    private Long id;
    private String name;
    private String email;
    private String address;
}
