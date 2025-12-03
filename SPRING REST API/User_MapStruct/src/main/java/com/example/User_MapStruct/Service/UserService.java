package com.example.User_MapStruct.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.User_MapStruct.Dto.UserRequest;
import com.example.User_MapStruct.Dto.UserResponse;
import com.example.User_MapStruct.Exception.BadRequestException;
import com.example.User_MapStruct.Exception.ResourceNotFoundException;
import com.example.User_MapStruct.Mapper.UserMapper;
import com.example.User_MapStruct.Model.User;
import com.example.User_MapStruct.Repo.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public List<UserResponse> getAll() {
        return mapper.toList(repository.findAll());
    }

    @Override
    public Page<UserResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    public UserResponse getById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy User có id = " + id));
        return mapper.toResponse(user);
    }

    @Override
    public UserResponse create(UserRequest request) {
        if (repository.existsByName(request.getName())) {
            throw new BadRequestException("Tên User đã tồn tại");
        }
        if (repository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email đã tồn tại");
        }
        User user = mapper.toUser(request);
        return mapper.toResponse(repository.save(user));
    }

    @Override
    public UserResponse update(UserRequest request, Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không timg thấy User có id = " + id));
        if (repository.existsByNameAndIdNot(request.getName(), id)) {
            throw new BadRequestException("Tên User đã tồn tại");
        }
        if (repository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new BadRequestException("Email User đã tồn tại");
        }
        mapper.update(user, request);
        return mapper.toResponse(repository.save(user));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Không tìm thấy User có id = " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public Page<UserResponse> getFeatured(Pageable pageable) {
        return repository.findByFeaturedTrue(pageable)
        .map(mapper::toResponse);
    }
}
