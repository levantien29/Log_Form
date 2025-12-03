package com.mycompany.myapp.service.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseId {
    private Long id;
    private String name;
    private String email;
    private String gender;
    private String studentCode;
    private LocalDate birthDate;
    private ClassRoomResponse classroom;
}
