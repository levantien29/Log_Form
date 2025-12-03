package com.example.Rest_PerSon.Service;

import java.util.List;

import com.example.Rest_PerSon.Dto.PersonReponse;
import com.example.Rest_PerSon.Dto.PersonRequest;

public interface IPersonService {
    List<PersonReponse> getAll();
    public PersonReponse create(PersonRequest request);
    public PersonReponse getByid(Long id);
    public PersonReponse update(Long id, PersonRequest request);
    public void delete(Long id);
}
