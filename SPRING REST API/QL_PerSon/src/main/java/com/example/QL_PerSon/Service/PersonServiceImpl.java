package com.example.QL_PerSon.Service;

import java.util.List;
import java.util.Optional;

import com.example.QL_PerSon.Model.Person;

public interface PersonServiceImpl {
    //lấy toàn bộ danh sách
    List<Person> getAll();
    //thêm person
    Person add (Person person);
    //cập nhập person
    Person update (Person person, Long id);
    //xoá person
    void delete (Long id);
    //tìm theo id trả về có hoặc không có
    Optional<Person> getByid(Long id);
}
