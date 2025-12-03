package com.mycompany.myapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mycompany.myapp.service.dto.SubjectDTO;
import com.mycompany.myapp.service.dto.SubjectRequest;
import com.mycompany.myapp.service.dto.SubjectResponse;
import com.mycompany.myapp.service.dto.SubjectResponseId;

public interface SubjectService {
    Page<SubjectDTO> getAll(Pageable pageable);
    public SubjectResponseId getById(Long id);
    public SubjectResponseId create(SubjectRequest request);
    public SubjectResponseId update(SubjectRequest request, Long id);
    void delete(Long id);
    List<SubjectResponse> getSubjectByTeacher(Long teacherId);

}
