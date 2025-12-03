package com.example.User_Rest.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.User_Rest.Dto.UserRequest;
import com.example.User_Rest.Dto.UserResponse;
import com.example.User_Rest.Service.IUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService iService;

    @GetMapping
    public List<UserResponse> getAll(){
        return iService.getAll();
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id){
        return iService.getById(id);
    }

    @PostMapping
    public UserResponse create(@Valid @RequestBody UserRequest request){
        return iService.create(request);
    }

    @PutMapping("/{id}")
    public UserResponse update(@Valid @RequestBody UserRequest request, @PathVariable Long id){
        return iService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        iService.delete(id);
    }
}
