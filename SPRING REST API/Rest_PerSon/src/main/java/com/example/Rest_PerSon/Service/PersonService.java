package com.example.Rest_PerSon.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Rest_PerSon.Dto.PersonReponse;
import com.example.Rest_PerSon.Dto.PersonRequest;
import com.example.Rest_PerSon.Exception.BadRequestException;
import com.example.Rest_PerSon.Exception.ResourceNotFoundException;
import com.example.Rest_PerSon.Mapper.PersonMapper;
import com.example.Rest_PerSon.Model.Person;
import com.example.Rest_PerSon.Repository.PersonRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService implements IPersonService {
    private final PersonRepository repository;

    public List<PersonReponse> getAll() {
        return repository.findAll()
                .stream()
                .map(PersonMapper::toReponse)
                .collect(Collectors.toList());
    }

    public PersonReponse getByid(Long id) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("khong tim thay id = " + id));
        return PersonMapper.toReponse(person);
    }

    public PersonReponse create(PersonRequest request) {
        if (repository.existsByName(request.getName())) {
            throw new BadRequestException("Ten da ton tai");
        }
        Person person = PersonMapper.toPerson(request);
        return PersonMapper.toReponse(repository.save(person));
    }

    public PersonReponse update(Long id, PersonRequest request) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("khong tim thay id = " + id));
        PersonMapper.update(person, request);
        return PersonMapper.toReponse(repository.save(person));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("khong tim thay id = " + id);
        }
        repository.deleteById(id);
    }
}
