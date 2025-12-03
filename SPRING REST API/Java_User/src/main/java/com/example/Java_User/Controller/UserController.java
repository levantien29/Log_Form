package com.example.Java_User.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Java_User.Dto.UserReponse;
import com.example.Java_User.Dto.UserRequest;
import com.example.Java_User.Service.IUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/userr")
@RequiredArgsConstructor
public class UserController {
    private final IUserService iUserservice;

    @GetMapping
    public List<UserReponse> getAll(){
        return iUserservice.getAll();
    }

    @PostMapping
    public UserReponse create(@Valid @RequestBody UserRequest request){
        return iUserservice.create(request);
    }

    @GetMapping("/{id}")
    public UserReponse getByid(@PathVariable Long id){
        return iUserservice.getByid(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        iUserservice.delete(id);
    }

    @PutMapping("/{id}")
    public UserReponse update(@PathVariable Long id, @RequestBody UserRequest request){
        return iUserservice.update(id, request);
    }
}
