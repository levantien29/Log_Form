package com.example.Reader_Map.Mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.Reader_Map.DTO.ReaderRequest;
import com.example.Reader_Map.DTO.ReaderResponse;
import com.example.Reader_Map.Model.Reader;
@Mapper(componentModel = "spring")
public interface ReaderMapper {
 public Reader toEntity(ReaderRequest readerRequest);

    // Lisst
    public List<ReaderResponse> toList(List<Reader> readers);

    // toResponse
    public ReaderResponse toResponse(Reader reader);

    // sua
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void toUpdate(@MappingTarget Reader reader, ReaderRequest readerRequest);
}
