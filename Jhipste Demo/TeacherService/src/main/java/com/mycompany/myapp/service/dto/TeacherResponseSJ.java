package com.mycompany.myapp.service.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherResponseSJ {
    private Long id;
    private String teacherName;
    private String email;
    private String phone;
    private String department;
    private List<SubjectResponse> subjects;
}
