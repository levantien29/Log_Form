package com.example.User_MapStruct.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.User_MapStruct.Dto.UserRequest;
import com.example.User_MapStruct.Dto.UserResponse;

public interface IUserService {
    public List<UserResponse> getAll();
    public Page<UserResponse> getAll(Pageable pageable);
    public UserResponse getById(Long id);
    public UserResponse create(UserRequest request);
    public UserResponse update(UserRequest request, Long id);
    public void delete(Long id);
    public Page<UserResponse> getFeatured(Pageable pageable);
}
