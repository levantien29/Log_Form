package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.client.StudentClient;
import com.mycompany.myapp.client.TeacherClient;
import com.mycompany.myapp.domain.ClassRoom;
import com.mycompany.myapp.repository.ClassRoomRepository;
import com.mycompany.myapp.service.ClassRoomService;
import com.mycompany.myapp.service.dto.ClassRoomDTO;
import com.mycompany.myapp.service.dto.ClassRoomRequest;
import com.mycompany.myapp.service.dto.ClassRoomResponseId;
import com.mycompany.myapp.service.dto.ClassRoomResponseTc;
import com.mycompany.myapp.service.dto.StudentResponse;
import com.mycompany.myapp.service.dto.TeacherResponse;
import com.mycompany.myapp.service.mapper.ClassRoomMapper;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.checkerframework.checker.units.qual.t;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing
 * {@link com.mycompany.myapp.domain.ClassRoom}.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ClassRoomServiceImpl implements ClassRoomService {

    private static final Logger LOG = LoggerFactory.getLogger(ClassRoomServiceImpl.class);

    private final ClassRoomRepository classRoomRepository;

    private final ClassRoomMapper classRoomMapper;

    private final StudentClient studentClient;

    private final TeacherClient teacherClient;

    @Override
    public ClassRoomResponseTc save(ClassRoomRequest request) {
        LOG.debug("Request to save ClassRoom : {}", request);
        if (classRoomRepository.existsByClassName(request.getClassName())) {
            throw new BadRequestAlertException("Lop da ton tai", "ClassRoom", "classExists");
        }
        TeacherResponse teacher = null;
        if (request.getTeacherId() != null) {
            try {
                teacher = teacherClient.getTeacherById(request.getTeacherId());
            } catch (Exception e) {
                // TODO: handle exception
                throw new BadRequestAlertException("Khong tim thay giao vien co id : "+ request.getTeacherId(), "ClassRoom", "idNotFound");
            }
        }
        ClassRoom classRoom = classRoomMapper.toEntity(request);
        ClassRoom saved = classRoomRepository.save(classRoom);
        ClassRoomResponseTc responseTc = classRoomMapper.toResponseTc(saved);
        responseTc.setTeacher(teacher);
        return responseTc;
    }

    @Override
    public ClassRoomDTO update(ClassRoomRequest request) {
        LOG.debug("Request to update ClassRoom : {}", request);
        ClassRoom classRoom = classRoomMapper.toEntity(request);
        classRoom = classRoomRepository.save(classRoom);
        return classRoomMapper.toDto(classRoom);
    }

    @Override
    public ClassRoomResponseTc partialUpdate(ClassRoomRequest request, Long id) {
        LOG.debug("Request to partially update ClassRoom : {}", request);
        ClassRoom classRoom = classRoomRepository.findById(id)
        .orElseThrow(()-> new BadRequestAlertException("Khong tim thay lop hoc co id : " + id, "ClassRoom", "idNotFound"));
        if (classRoomRepository.existsByClassNameAndIdNot(request.getClassName(), id)) {
            throw new BadRequestAlertException("Lop hoc da ton tai", "ClassRoom", "classExist");
        }
        TeacherResponse teacher = null;
        if (request.getTeacherId() != null) {
            try {
                teacher = teacherClient.getTeacherById(request.getTeacherId());
            } catch (Exception e) {
                // TODO: handle exception
                throw new BadRequestAlertException("Khong tim thay giao vien co id : " + id, "ClassRoom", "idNotFound");
            }
        }
        classRoomMapper.partialUpdate(classRoom, request);
        ClassRoom saved = classRoomRepository.save(classRoom);
        ClassRoomResponseTc responseTc = classRoomMapper.toResponseTc(saved);
        responseTc.setTeacher(teacher);
        return responseTc;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClassRoomDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all ClassRooms");
        return classRoomRepository.findAll(pageable).map(classRoomMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ClassRoomResponseId findOne(Long id) {
        LOG.debug("Request to get ClassRoom : {}", id);
        ClassRoom classRoom = classRoomRepository.findById(id)
                .orElseThrow(() -> new BadRequestAlertException("Khong tim thay lop hoc co id : " + id, "classroom",
                        "idNotFound"));
        ClassRoomResponseId response = classRoomMapper.toResponseId(classRoom);

        try {
            List<StudentResponse> student = studentClient.getStudentByClassroom(id);
            response.setStudents(student);
        } catch (Exception e) {
            // TODO: handle exception
            throw new BadRequestAlertException("Khong tim thay sinh vien co id : " + id, "ClassRoom", "idNotFound");
        }

        if (classRoom.getTeacherId() != null) {
            try {
                TeacherResponse teacher = teacherClient.getTeacherById(classRoom.getTeacherId());
                response.setTeachers(teacher);
            } catch (Exception e) {
                // TODO: handle exception
                throw new BadRequestAlertException("Khong tim thay giao vien co id : " + id, "ClassRoom", "idNotFound");
            }
        }
        return response;
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete ClassRoom : {}", id);
        classRoomRepository.deleteById(id);
    }
}
