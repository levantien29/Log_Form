package com.example.Phone_MapStruct.Mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.Phone_MapStruct.Dto.PhoneRequest;
import com.example.Phone_MapStruct.Dto.PhoneResponse;
import com.example.Phone_MapStruct.Model.Phone;

@Mapper(componentModel = "spring")
public interface PhoneMapper {
    public Phone toEntity(PhoneRequest request);
    public PhoneResponse toResponse(Phone phone);
    public List<PhoneResponse> toList(List<Phone> phone);

    //bỏ qua các file null
    //Dùng để bỏ qua các giá trị null từ UserRequest khi map sang User
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    //@MappingTarget xác định ttham số User là đối tươg đích cần cập nhập không thêm mới
    void update(@MappingTarget Phone phone, PhoneRequest request);
}
