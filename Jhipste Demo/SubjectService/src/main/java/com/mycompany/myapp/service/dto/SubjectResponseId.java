package com.mycompany.myapp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResponseId {
    private Long id;
    private String subjectName;
    private Long credit;
    private TeacherResponse teacher;
}
