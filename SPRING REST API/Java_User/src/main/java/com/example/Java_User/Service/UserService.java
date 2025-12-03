package com.example.Java_User.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Java_User.Dto.UserReponse;
import com.example.Java_User.Dto.UserRequest;
import com.example.Java_User.Mapper.UserMapper;
import com.example.Java_User.Model.User;
import com.example.Java_User.Repository.UserRepository;
import com.example.Java_User.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository repository;
//lays toan bo danh sach
    @Override
    public List<UserReponse>getAll(){
        return repository.findAll()
        .stream()
        .map(UserMapper::toReponse)
        .collect(Collectors.toList());
    }

    //add
    public UserReponse create(UserRequest request){
        User user = UserMapper.toEntity(request);
        return UserMapper.toReponse(repository.save(user));
    }
    
    //lay theo idd
    @Override
    public UserReponse getByid(Long id){
        User user = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("khong tim thay id = "+ id));
        return UserMapper.toReponse(user);
    }

    //ham updatee
    @Override
    public UserReponse update(Long id, UserRequest request){
        User user = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("khong tim thay id = " + id));
        UserMapper.UpdateEntity(user, request);
        return UserMapper.toReponse(repository.save(user));
    }

    //delete
    @Override
    public void delete(Long id){
        repository.deleteById(id);
    }
}
