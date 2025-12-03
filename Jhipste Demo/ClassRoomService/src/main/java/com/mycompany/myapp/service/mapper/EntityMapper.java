package com.mycompany.myapp.service.mapper;

import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.mycompany.myapp.service.dto.ClassRoomRequest;
import com.mycompany.myapp.service.dto.ClassRoomResponseId;
import com.mycompany.myapp.service.dto.ClassRoomResponseTc;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */

public interface EntityMapper<ClassRoomDTO, ClassRoom> {
    ClassRoom toEntity(ClassRoomRequest dto);

    ClassRoomDTO toDto(ClassRoom entity);

    ClassRoomResponseId toResponseId(ClassRoom classRoom);

    ClassRoomResponseTc toResponseTc(ClassRoom classRoom);

    List<ClassRoomDTO> toEntity(List<ClassRoom> dtoList);

    List<ClassRoom> toDto(List<ClassRoomRequest> entityList);

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget ClassRoom entity, ClassRoomRequest dto);
}
