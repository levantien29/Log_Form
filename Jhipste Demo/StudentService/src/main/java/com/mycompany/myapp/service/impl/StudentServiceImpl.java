package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.client.ClassRoomClient;
import com.mycompany.myapp.domain.Student;
import com.mycompany.myapp.repository.StudentRepository;
import com.mycompany.myapp.service.StudentService;
import com.mycompany.myapp.service.dto.ClassRoomResponse;
import com.mycompany.myapp.service.dto.StudentRequest;
import com.mycompany.myapp.service.dto.StudentResponse;
import com.mycompany.myapp.service.dto.StudentResponseId;
import com.mycompany.myapp.service.mapper.StudentMap;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
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
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;
    private final StudentMap mapper;
    private final ClassRoomClient classRoomClient;

    @Override
    public Page<StudentResponse> getAll(Pageable pageable){
        return repository.findAll(pageable).map(mapper::toResponseAll);
    }


    //lay theo id
    @Override
    public StudentResponseId getById(Long id){
        Student student = repository.findById(id)
        .orElseThrow(() -> new BadRequestAlertException("Khong tim thay sinh vien co id : " + id, "student", "idnotfound"));

        StudentResponseId responseId = mapper.toResponseId(student);
        if (student.getClassroomId() != null) {
            ClassRoomResponse classroom = classRoomClient.getClassRoomById(student.getClassroomId());
            responseId.setClassroom(classroom);
        }
        return responseId;
    }


    //them student
    @Override
    public StudentResponseId create(StudentRequest request){
        if (repository.existsByEmail(request.getEmail())) {
            throw new BadRequestAlertException("Email da ton tai", "student", "emailNotfound");
        }
        if (repository.existsByStudentCode(request.getStudentCode())) {
            throw new BadRequestAlertException("Ma sinh vien da ton tai", "student", "studentcodeNotfound");
        }
        ClassRoomResponse classroom = null;
        if (request.getClassroomId() != null) {
            try {
                classroom =  classRoomClient.getClassRoomById(request.getClassroomId());
            } catch (Exception e) {
                // TODO: handle exception
                throw new BadRequestAlertException("Khong tim thay class co id : " + request.getClassroomId(), "Student", "idNotFound");
            }
        }
        Student student = mapper.toEntity(request);
        Student saved = repository.save(student);
        StudentResponseId responseId = mapper.toResponseId(saved);
        responseId.setClassroom(classroom);
        return responseId;
    }

    //update
    public StudentResponseId update(StudentRequest request, Long id){
        Student student = repository.findById(id)
        .orElseThrow(()-> new BadRequestAlertException("Khong tim thay sinh vien co id : " + id, "student", "idnotfound"));
        if (repository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new BadRequestAlertException("Email da ton tai", "student", "emailNotfound");
        }
        if (repository.existsByStudentCodeAndIdNot(request.getStudentCode(), id)) {
            throw new BadRequestAlertException("Ma sinh vien da ton tai", "student", "studentCodeNotFound");
        }
        ClassRoomResponse classroom = null;
        if (request.getClassroomId() != null) {
            try {
                classroom = classRoomClient.getClassRoomById(request.getClassroomId());
            } catch (Exception e) {
                // TODO: handle exception
                throw new BadRequestAlertException("Khong tim thay classroom co id : " + request.getClassroomId(), "Student", "idNotFound");
            }
        }
        mapper.updateStudent(student, request);
        Student saved = repository.save(student);
        StudentResponseId responseId = mapper.toResponseId(saved);
        responseId.setClassroom(classroom);
        return responseId;
    }

    public void delete(Long id){
        Student student = repository.findById(id)
        .orElseThrow(()-> new BadRequestAlertException("Khong tim thay sinh vien co id : " + id, "student", "idNotFound"));
        repository.delete(student);
    }

    @Override
    public List<StudentResponse> getStudentsByClassroom(Long classroomId){
        List<Student> students = repository.findByClassroomId(classroomId);
        return students.stream()
        .map(mapper::toResponseAll)
        .collect(Collectors.toList());
    }
}
