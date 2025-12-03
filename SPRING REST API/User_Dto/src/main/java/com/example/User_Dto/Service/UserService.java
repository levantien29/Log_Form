package com.example.User_Dto.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.User_Dto.Dto.UserReponse;
import com.example.User_Dto.Dto.UserRequest;
import com.example.User_Dto.Model.User;
import com.example.User_Dto.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;

    public UserReponse ToReponse(User user){
        return new UserReponse(user.getId(), user.getName(), user.getAge());
    }

    @Override
    public List<UserReponse> getAll(){
        return userRepository.findAll()
        .stream()
        .map(this::ToReponse)
        .collect(Collectors.toList());
    }

    @Override
    public UserReponse update(Long id, UserRequest request){
        User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Not Found"));
        user.setName(request.getName());
        user.setAge(request.getAge());
        user.setPassword(request.getPassword());
        return ToReponse(userRepository.save(user));
    }

    @Override
    public UserReponse getByid(Long id){
        User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Not Found"));
        return ToReponse(user);
    }

    @Override
    public void delete(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public UserReponse create(UserRequest request){
        User user = new User(null, request.getName(), request.getAge(), request.getPassword());
        return ToReponse(userRepository.save(user));
    }
}
