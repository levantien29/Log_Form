package com.example.Animal_Rest.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Animal_Rest.Dto.AnimalRequest;
import com.example.Animal_Rest.Dto.AnimalResponse;
import com.example.Animal_Rest.Service.IAnimalService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/animal")
@RequiredArgsConstructor
public class AnimalController {
    private final IAnimalService service;

    @GetMapping
    public List<AnimalResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AnimalResponse getByid(@PathVariable Long id){
        return service.getByid(id);
    }

    @PostMapping
    public AnimalResponse create(@Valid @RequestBody AnimalRequest request){
        return service.create(request);
    }

    @PutMapping("/{id}")
    public AnimalResponse update(@Valid @RequestBody AnimalRequest request, @PathVariable Long id){
        return service.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
