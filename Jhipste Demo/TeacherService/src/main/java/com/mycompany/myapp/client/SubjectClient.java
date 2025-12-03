package com.mycompany.myapp.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mycompany.myapp.config.FeignClientConfig;
import com.mycompany.myapp.service.dto.SubjectResponse;

@FeignClient(name = "subjectService", configuration = FeignClientConfig.class)
public interface SubjectClient {
    @GetMapping("/api/subjects/teacher/{teacherId}")
    List<SubjectResponse> getSubjectByTeacher(@PathVariable ("teacherId") Long teacherId);
}
