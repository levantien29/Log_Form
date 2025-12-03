package com.example.Reader_Map.DTO;

import java.time.LocalDate;
import lombok.Data;
@Data
public class ReaderSearchRequest {
    private String name;
    private String email;
    private String phone;
    private String address;
    private LocalDate registeredDate;
    private String status;
}
