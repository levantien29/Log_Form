package com.example.Rest_PerSon.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Rest_PerSon.Dto.PersonReponse;
import com.example.Rest_PerSon.Dto.PersonRequest;
import com.example.Rest_PerSon.Service.IPersonService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {
    private final IPersonService service;

    @GetMapping
    public List<PersonReponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PersonReponse getByid(@PathVariable Long id){
        return service.getByid(id);
    }

    @PostMapping
    public PersonReponse create(@Valid @RequestBody PersonRequest request){
        return service.create(request);
    }

    @PutMapping("/{id}")
    public PersonReponse update(@PathVariable Long id,@Valid @RequestBody PersonRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id); 
    }
}
