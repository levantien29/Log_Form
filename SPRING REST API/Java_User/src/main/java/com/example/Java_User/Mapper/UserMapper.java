package com.example.Java_User.Mapper;

import com.example.Java_User.Dto.UserReponse;
import com.example.Java_User.Dto.UserRequest;
import com.example.Java_User.Model.User;
//chuyển đổi entity -> dto
public class UserMapper {
    //chuyển từ dto -> user để lưu db
    public static User toEntity(UserRequest request){
        return new User(null, request.getName(), request.getAge(), request.getAddress(), request.getPassword());
    }

    //chuyển từ user sang dto để trả về client
    public static UserReponse toReponse(User user){
        return new UserReponse(user.getId(), user.getName(), user.getAge(), user.getAddress());
    }

    //Cập nhật dữ liệu từ DTO vào entity đang tồn tại
    public static void UpdateEntity(User user, UserRequest request){
        user.setName(request.getName());
        user.setAge(request.getAge());
        user.setAddress(request.getAddress());
        user.setPassword(request.getPassword());
    }
}
