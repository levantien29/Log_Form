package com.mycompany.myapp.service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.mycompany.myapp.domain.Student;
import com.mycompany.myapp.service.dto.StudentRequest;
import com.mycompany.myapp.service.dto.StudentResponse;
import com.mycompany.myapp.service.dto.StudentResponseId;

@Mapper(componentModel = "spring")
public interface StudentMap {
    Student toEntity(StudentRequest request);

    StudentResponse toResponseAll(Student student);

    StudentResponseId toResponseId(Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudent(@MappingTarget Student student, StudentRequest request);

}
