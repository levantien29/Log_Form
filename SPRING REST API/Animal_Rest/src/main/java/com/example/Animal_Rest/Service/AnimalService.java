package com.example.Animal_Rest.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.Animal_Rest.Exception.BadRequestException;
import com.example.Animal_Rest.Exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import com.example.Animal_Rest.Dto.AnimalRequest;
import com.example.Animal_Rest.Dto.AnimalResponse;
import com.example.Animal_Rest.Mapper.AnimalMapper;
import com.example.Animal_Rest.Model.Animal;
import com.example.Animal_Rest.Repository.AnimalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimalService implements IAnimalService{
    private final AnimalRepository repository;


    public List<AnimalResponse> getAll(){
        return repository.findAll()
        .stream()
        .map(AnimalMapper::toResponse)
        .collect(Collectors.toList());
    }

    //tìm kiếm theo id
    public AnimalResponse getByid(Long id){
        Animal animal = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Animal có id = " + id));
        return AnimalMapper.toResponse(animal);
    }

    public AnimalResponse create(AnimalRequest request){
        if (repository.existsByName(request.getName())) {
            throw new BadRequestException("Tên đã tồn tại");
        }
        Animal animal = AnimalMapper.toAnimal(request);
        return AnimalMapper.toResponse(repository.save(animal));
    }

    public AnimalResponse update(AnimalRequest request, Long id){
        Animal animal = repository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Không tìm thấy id = " + id));
        AnimalMapper.update(animal, request);
        return AnimalMapper.toResponse(repository.save(animal));
    }

    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Không tìm thấy id = " +id);
        }
        repository.deleteById(id);
    }
}
