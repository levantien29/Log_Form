package com.example.Demo_DTO.Service;

import java.util.List;

import com.example.Demo_DTO.DTO.PeopleRepose;
import com.example.Demo_DTO.DTO.PeopleRequest;

public interface IPeopleService {
    List<PeopleRepose> getAll();
    PeopleRepose create (PeopleRequest peopleRequest);
    PeopleRepose getByid (Long id);
    PeopleRepose update(Long id, PeopleRequest peopleRequest);
    void delete(Long id);
}
