package com.example.QL_PerSon.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.QL_PerSon.Model.Person;
import com.example.QL_PerSon.Repository.PersonRepository;

@Service
public class PersonService implements PersonServiceImpl{
    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getAll(){
        return personRepository.findAll();
    }

    @Override
    public Person add(Person person){
        return personRepository.save(person);
    }

    @Override
    public void delete(Long id){
         personRepository.deleteById(id);
    }

    @Override
    public Optional<Person> getByid(Long id){
        return personRepository.findById(id);
    }

    @Override
    public Person update(Person person, Long id){
        Person ps = personRepository.findById(id).orElseThrow(()
         -> new ResponseStatusException(HttpStatus.NOT_FOUND,"khong tim thay nguoi dung co id" + id));
        ps.setName(person.getName());
        ps.setEmail(person.getEmail());
        ps.setAddress(person.getAddress());
         return personRepository.save(ps);
    }
}
