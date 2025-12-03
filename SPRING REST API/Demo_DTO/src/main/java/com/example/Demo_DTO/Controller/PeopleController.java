package com.example.Demo_DTO.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Demo_DTO.DTO.PeopleRepose;
import com.example.Demo_DTO.DTO.PeopleRequest;
import com.example.Demo_DTO.Service.IPeopleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/people")
@RequiredArgsConstructor // DÙNG THAY THẾ CHO CONTRUCSTOR THỦ CÔNG
public class PeopleController {
    private final IPeopleService ipeopleService;

    // public PeopleController(IPeopleService peopleService) {
    // this.ipeopleService = peopleService;
    // }

    @GetMapping
    public List<PeopleRepose> getAll() {
        return ipeopleService.getAll();
    }

    @GetMapping("/{id}")
    public PeopleRepose getByid(@PathVariable Long id) {
        return ipeopleService.getByid(id);
    }

    @PostMapping
    public PeopleRepose create(@RequestBody PeopleRequest request) {
        return ipeopleService.create(request);
    }

    @PutMapping("/{id}")
    public PeopleRepose update(@PathVariable Long id, @RequestBody PeopleRequest request) {
        return ipeopleService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ipeopleService.delete(id);
    }

 }
