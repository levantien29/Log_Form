package com.mycompany.myapp.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mycompany.myapp.config.FeignClientConfig;
import com.mycompany.myapp.service.dto.StudentResponse;

@FeignClient(name = "studentService", configuration = FeignClientConfig.class)
public interface StudentClient {
    @GetMapping("/api/students/classroom/{classroomId}")
    List<StudentResponse> getStudentByClassroom(@PathVariable("classroomId") Long classroomId);
}
