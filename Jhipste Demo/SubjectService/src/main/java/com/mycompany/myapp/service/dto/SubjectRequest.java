package com.mycompany.myapp.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SubjectRequest {

    @NotBlank(message = "Ten mon hoc khong duoc de trong")
    private String subjectName;

    @NotNull(message = "So tin chi khong duoc de trong")
    private int credit;

    @NotNull(message = "Id giao vien khong duoc de trong")
    private Long teacherId;
}
