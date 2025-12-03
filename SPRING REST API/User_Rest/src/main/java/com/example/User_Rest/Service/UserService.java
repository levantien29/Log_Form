package com.example.User_Rest.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.User_Rest.Dto.UserRequest;
import com.example.User_Rest.Dto.UserResponse;
import com.example.User_Rest.Exception.BadRequestException;
import com.example.User_Rest.Exception.ResourceNotFoundException;
import com.example.User_Rest.Mapper.UserMapper;
import com.example.User_Rest.Model.User;
import com.example.User_Rest.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository repository;

    public List<UserResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy User có id =" + id));
        return UserMapper.toResponse(user);
    }

    public UserResponse create(UserRequest request) {
        if (repository.existsByName(request.getName())) {
            throw new BadRequestException("Tên đã tồn tại");
        }
        if (repository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email Đã tồn tại");
        }
        User user = UserMapper.toUser(request);
        return UserMapper.toResponse(repository.save(user));
    }

    public UserResponse update(UserRequest request, Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy User có id = " + id));
        if (repository.existsByNameAndIdNot(request.getName(), id)) {
            throw new BadRequestException("Tên đã tồn tại, Vui lòng thêm tên khác !");
        }
        if (repository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new BadRequestException("Email Đã tồn tại, Vui lòng thêm Email khác !");
        }

        UserMapper.toUpdate(user, request);
        return UserMapper.toResponse(repository.save(user));
    }

    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Không tìm thấy User có id = "+ id);
        }
        repository.deleteById(id);
    }
}
