package com.example.User_Dto.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.User_Dto.Dto.UserReponse;
import com.example.User_Dto.Dto.UserRequest;
import com.example.User_Dto.Service.IUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService iUserService;

    @GetMapping
    public List<UserReponse> getAll(){
        return iUserService.getAll();
    }

    @GetMapping("/{id}")
    public UserReponse getByid(@PathVariable Long id){
        return iUserService.getByid(id);
    }

    @PutMapping("/{id}")
    public UserReponse update(@PathVariable Long id, @RequestBody UserRequest request){
        return iUserService.update(id, request);
    }

    @PostMapping
    public UserReponse create(@RequestBody UserRequest request){
        return iUserService.create(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        iUserService.delete(id);
    }
}
