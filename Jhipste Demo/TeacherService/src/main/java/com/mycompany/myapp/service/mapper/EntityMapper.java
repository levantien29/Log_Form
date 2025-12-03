package com.mycompany.myapp.service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.mycompany.myapp.domain.Teacher;
import com.mycompany.myapp.service.dto.TeacherDTO;
import com.mycompany.myapp.service.dto.TeacherRequest;
import com.mycompany.myapp.service.dto.TeacherResponseSJ;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    Teacher toEntity(TeacherRequest request);

    TeacherDTO toResponse(Teacher teacher);

    TeacherResponseSJ toResponseSj(Teacher teacher);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void toUpdate(@MappingTarget Teacher teacher, TeacherRequest request);
}