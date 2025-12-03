package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.client.TeacherClient;
import com.mycompany.myapp.domain.Subject;
import com.mycompany.myapp.repository.SubjectRepository;
import com.mycompany.myapp.service.SubjectService;
import com.mycompany.myapp.service.dto.SubjectDTO;
import com.mycompany.myapp.service.dto.SubjectRequest;
import com.mycompany.myapp.service.dto.SubjectResponse;
import com.mycompany.myapp.service.dto.SubjectResponseId;
import com.mycompany.myapp.service.dto.TeacherResponse;
import com.mycompany.myapp.service.mapper.EntityMapper;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import feign.FeignException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository repository;

    private final EntityMapper mapper;

    private final TeacherClient teacherClient;

    @Override
    public Page<SubjectDTO> getAll(Pageable pageable){
        return repository.findAll(pageable).map(mapper::toResponse);
    }
    
    @Override
    public SubjectResponseId getById(Long id){
        Subject subject = repository.findById(id)
        .orElseThrow(()-> new BadRequestAlertException("Khong tim thay mon hoc co id : " + id, "subject", "idNotFound"));
        SubjectResponseId responseId = mapper.tResponseId(subject);
        if(subject.getTeacherId() != null){
            TeacherResponse teacherResponse = teacherClient.getTeacherById(subject.getTeacherId());
            responseId.setTeacher(teacherResponse);
        }
        return responseId;
    }

    @Override
    public SubjectResponseId create(SubjectRequest request){
        if (repository.existsBySubjectName(request.getSubjectName())) {
            throw new BadRequestAlertException("Mon hoc da ton tai", "subject", "subjectAlreadyExirts");
        }
        TeacherResponse teacherResponse = null;
        try {
            teacherResponse = teacherClient.getTeacherById(request.getTeacherId());
        } catch (FeignException.NotFound e) {
            // TODO: handle exception
            throw new BadRequestAlertException("Khong tim thay teacher co id : " + request.getTeacherId(), "subject", "idNotFound");
        }
        Subject subject = mapper.toEntity(request);
        Subject saver = repository.save(subject);
        SubjectResponseId response = mapper.tResponseId(saver);
        response.setTeacher(teacherResponse);
        return response;
    }

    @Override
    public SubjectResponseId update(SubjectRequest request, Long id){
        Subject subject = repository.findById(id)
        .orElseThrow(()-> new BadRequestAlertException("Khong tim thay mon hoc co id : " + id, "subject", "inNotFound"));
        if (repository.existsBySubjectNameAndIdNot(request.getSubjectName(), id)) {
            throw new BadRequestAlertException("Mon hoc da ton tai", "subject", "subjectAlreadyExists");
        }
        TeacherResponse teacher = null;
        try {
            teacher = teacherClient.getTeacherById(request.getTeacherId());
        } catch (Exception e) {
            // TODO: handle exception
            throw new BadRequestAlertException("Khong tim thay giao vien co id : " + request.getTeacherId(), "subject", "idNotFound");
        }
        mapper.toUpdate(subject, request);
        Subject saver = repository.save(subject);
        SubjectResponseId responseId = mapper.tResponseId(subject);
        responseId.setTeacher(teacher);
        return responseId;
    }
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<SubjectResponse> getSubjectByTeacher(Long teacherId){
        List<Subject> subjects = repository.findByTeacherId(teacherId);
        return subjects.stream()
        .map(mapper::toResponseTC)
        .collect(Collectors.toList());
    }
}
