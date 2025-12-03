package com.mycompany.myapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mycompany.myapp.service.dto.TeacherDTO;
import com.mycompany.myapp.service.dto.TeacherRequest;
import com.mycompany.myapp.service.dto.TeacherResponseSJ;

public interface TeacherService {
    Page<TeacherDTO> getAll(Pageable pageable);
    public TeacherResponseSJ getById(Long id);
    public TeacherDTO create(TeacherRequest request);
    public TeacherDTO update(TeacherRequest request, Long id);
    void delete(Long id);
}
