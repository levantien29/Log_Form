package com.example.User_Dto.Service;

import java.util.List;

import com.example.User_Dto.Dto.UserReponse;
import com.example.User_Dto.Dto.UserRequest;

public interface IUserService {
    List<UserReponse> getAll();
    UserReponse create(UserRequest request);
    UserReponse update(Long id, UserRequest request);
    UserReponse getByid(Long id);
    void delete(Long id);
}