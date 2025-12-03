package com.example.Animal_Rest.Service;

import java.util.List;

import com.example.Animal_Rest.Dto.AnimalRequest;
import com.example.Animal_Rest.Dto.AnimalResponse;

public interface IAnimalService {
    List<AnimalResponse> getAll();
    public AnimalResponse getByid(Long id);
    public AnimalResponse update(AnimalRequest request, Long id);
    public AnimalResponse create(AnimalRequest request);
    public void delete(Long id);
}
