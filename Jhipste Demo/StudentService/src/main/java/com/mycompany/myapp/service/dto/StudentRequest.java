package com.mycompany.myapp.service.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Name cannot be blank")
    @Email(message = "Email is not in the correct format.")
    private String email;

    @NotBlank(message = "Gender cannot be blank")
    private String gender;

    @NotBlank(message = "studentCode cannot be blank")
    private String studentCode;

    @NotNull(message = "birthDate is required")
    private LocalDate birthDate;

    @NotNull(message = "classroomId is required")
    private Long classroomId;
}
