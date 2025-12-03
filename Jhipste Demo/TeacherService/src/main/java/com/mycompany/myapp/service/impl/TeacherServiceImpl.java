package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.client.SubjectClient;
import com.mycompany.myapp.domain.Teacher;
import com.mycompany.myapp.repository.TeacherRepository;
import com.mycompany.myapp.service.TeacherService;
import com.mycompany.myapp.service.dto.SubjectResponse;
import com.mycompany.myapp.service.dto.TeacherDTO;
import com.mycompany.myapp.service.dto.TeacherRequest;
import com.mycompany.myapp.service.dto.TeacherResponseSJ;
import com.mycompany.myapp.service.mapper.EntityMapper;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository repository;
    private final EntityMapper mapper;
    private final SubjectClient subjectClient;

    @Override
    public Page<TeacherDTO> getAll(Pageable pageable){
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    public TeacherResponseSJ getById(Long id){
        Teacher teacher = repository.findById(id)
        .orElseThrow(()-> new BadRequestAlertException("Khong tim thay giao vien co id : " + id, "teacher", "idNotFound"));
        TeacherResponseSJ responseSJ = mapper.toResponseSj(teacher);
        try {
            List<SubjectResponse> subjects = subjectClient.getSubjectByTeacher(id);
            responseSJ.setSubjects(subjects);
        } catch (Exception e) {
            // TODO: handle exception
            responseSJ.setSubjects(List.of());
        }
        return responseSJ;
    }

    @Override
    public TeacherDTO create(TeacherRequest request){
        if (repository.existsByEmail(request.getEmail())) {
            throw new BadRequestAlertException("Email da ton tai", "email", "emailAlreadyExists");
        }
        Teacher teacher = mapper.toEntity(request);
        return mapper.toResponse(repository.save(teacher));
    }

    @Override
    public TeacherDTO update(TeacherRequest request, Long id){
       Teacher teacher = repository.findById(id)
       .orElseThrow(()-> new BadRequestAlertException("Khong tim thay giao vien co id :" + id, "teacher", "idNotFound"));
       if (repository.existsByEmailAndIdNot(request.getEmail(), id)) {
        throw new BadRequestAlertException("Email da ton tai", "teacher", "emailAlreadyExists");
       }
       mapper.toUpdate(teacher, request);
       return mapper.toResponse(repository.save(teacher));
    }

    @Override
    public void delete(Long id){
        Teacher teacher = repository.findById(id)
        .orElseThrow(()-> new BadRequestAlertException("Khong tim thay giao vien co id : " + id, "teacher", "idNotFound"));
        repository.delete(teacher);
    }
}
