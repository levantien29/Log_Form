package com.example.Java_User.Service;

import java.util.List;

import com.example.Java_User.Dto.UserReponse;
import com.example.Java_User.Dto.UserRequest;

public interface IUserService {
    List<UserReponse> getAll();
    public UserReponse create(UserRequest request);
    public UserReponse update(Long id, UserRequest request);
    public UserReponse getByid(Long id);
    public void delete(Long id);
}
