package com.mycompany.myapp.service.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassRoomResponseId {
    private Long id;
    private String className;
    private String department;
    private Integer year;

    private List<StudentResponse> students;
    private TeacherResponse teachers;
}
