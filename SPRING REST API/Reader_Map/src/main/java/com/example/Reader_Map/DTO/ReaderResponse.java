package com.example.Reader_Map.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReaderResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private LocalDate registeredDate;
    private String status;
}
