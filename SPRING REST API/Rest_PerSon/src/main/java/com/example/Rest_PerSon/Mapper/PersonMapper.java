package com.example.Rest_PerSon.Mapper;

import com.example.Rest_PerSon.Dto.PersonReponse;
import com.example.Rest_PerSon.Dto.PersonRequest;
import com.example.Rest_PerSon.Model.Person;

public class PersonMapper {
    //chuyển dl từ client (PersonRequest) thành person để lưu vào database
    //hàm thêm Animal
    public static Person toPerson(PersonRequest person){
        return new Person(null, person.getName(), person.getAge(), person.getAddress(), person.getPassword());
    }

    //hàm trả dữ liệu từ person sang (dto) ra client
    public static PersonReponse toReponse(Person person){
        return new PersonReponse(person.getId(), person.getName(), person.getAge(), person.getAddress());
    }

    //hàm update
    public static void update(Person person, PersonRequest request){
        person.setName(request.getName());
        person.setAge(request.getAge());
        person.setAddress(request.getAddress());
        person.setPassword(request.getPassword());
    }
}
