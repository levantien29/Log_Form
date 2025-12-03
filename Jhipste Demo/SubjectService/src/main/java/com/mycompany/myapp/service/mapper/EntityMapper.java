package com.mycompany.myapp.service.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.mycompany.myapp.domain.Subject;
import com.mycompany.myapp.service.dto.SubjectDTO;
import com.mycompany.myapp.service.dto.SubjectRequest;
import com.mycompany.myapp.service.dto.SubjectResponse;
import com.mycompany.myapp.service.dto.SubjectResponseId;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    Subject toEntity(SubjectRequest request);

    SubjectDTO toResponse(Subject subject);

    SubjectResponse toResponseTC(Subject subject);

    SubjectResponseId tResponseId(Subject subject);

    List<SubjectDTO> toList(List<Subject> subjects);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toUpdate(@MappingTarget Subject subject, SubjectRequest request);
}
