package com.example.User_MapStruct.Mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.User_MapStruct.Dto.UserRequest;
import com.example.User_MapStruct.Dto.UserResponse;
import com.example.User_MapStruct.Model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public User toUser(UserRequest request);
    public UserResponse toResponse(User user);
    public List<UserResponse> toList(List<User> users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget User user, UserRequest request);
}
