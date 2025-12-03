package com.mycompany.myapp.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassRoomRequest {
    @NotBlank(message = "Ten lop khong duoc de trong")
    private String className;

    @NotBlank(message = "Phong khong duoc de trong")
    private String department;

    @NotNull(message = "Nam hoc khong duoc de trong")
    private int year;

    @NotNull(message = "Id giao vien khong duoc de trong")
    private Long teacherId;
}
