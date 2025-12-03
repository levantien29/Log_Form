package com.example.User_Rest.Mapper;

import com.example.User_Rest.Dto.UserRequest;
import com.example.User_Rest.Dto.UserResponse;
import com.example.User_Rest.Model.User;

public class UserMapper {
    // theem
    public static User toUser(UserRequest request) {
        return new User(null, request.getName(), request.getAge(), request.getAddress(), request.getEmail(),
                request.getPassword());
    }

    // update
    public static void toUpdate(User user, UserRequest request) {
        user.setName(request.getName());
        user.setAge(request.getAge());
        user.setAddress(request.getAddress());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
    }

    //repponse
    public static UserResponse toResponse(User user){
        return new UserResponse(user.getId(), user.getName(), user.getAge(), user.getAddress(), user.getEmail());
    }
}
