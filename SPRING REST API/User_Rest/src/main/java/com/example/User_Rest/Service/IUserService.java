package com.example.User_Rest.Service;

import java.util.List;

import com.example.User_Rest.Dto.UserRequest;
import com.example.User_Rest.Dto.UserResponse;

public interface IUserService{
    List<UserResponse> getAll();
    public UserResponse getById(Long id);
    public UserResponse create(UserRequest request);
    public UserResponse update(UserRequest request, Long id);
    public void delete(Long id);
}
