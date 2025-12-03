package com.example.QL_PerSon.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.QL_PerSon.Model.Person;
import com.example.QL_PerSon.Service.PersonService;

@RestController // dl tra ve dang json
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person>getAll(){
        return personService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Person>getByid(@PathVariable Long id){
        return personService.getByid(id);
    }

    @PostMapping
    //RequestBody chuyển sl từ json sang 1 đối tượng java(test postman)
    public Person add(@RequestBody Person person){
        return personService.add(person);
    }

    @DeleteMapping("/{id}")
    //PathVariable truyền từ url(đường dẫn) vào id GET http://localhost:8080/person/5
    void delete(@PathVariable Long id){
        personService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) //phản hồi lại mã 200 nếu cập nhập thành công
    public Person update(@RequestBody Person person, @PathVariable Long id){
        return personService.update(person, id);
    }
}
