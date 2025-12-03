package com.mycompany.myapp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mycompany.myapp.config.FeignClientConfig;
import com.mycompany.myapp.service.dto.TeacherResponse;

@FeignClient(name = "teacherService", configuration = FeignClientConfig.class)
public interface TeacherClient {
    @GetMapping("/api/teachers/{id}")
    TeacherResponse getTeacherById(@PathVariable("id") Long teacherId);
}
