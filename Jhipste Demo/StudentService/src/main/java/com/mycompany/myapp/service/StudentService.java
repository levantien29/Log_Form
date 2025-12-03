package com.mycompany.myapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mycompany.myapp.service.dto.StudentRequest;
import com.mycompany.myapp.service.dto.StudentResponse;
import com.mycompany.myapp.service.dto.StudentResponseId;

public interface StudentService {
    public Page<StudentResponse> getAll(Pageable pageable);
    public StudentResponseId getById(Long id);
    public StudentResponseId create(StudentRequest request);
    public StudentResponseId update(StudentRequest request, Long id);
    public void delete(Long id);
    List<StudentResponse> getStudentsByClassroom(Long classroomId);
}
