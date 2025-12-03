package com.example.Quan_Li_Nhan_Su.Service;

import java.util.List;
import java.util.Optional;

import com.example.Quan_Li_Nhan_Su.Model.Student;

public interface IStudentService {
    List<Student> getAllStudent();
    Student add (Student student);
    Student update (Long id, Student student);
    void delete (Long id);
    Optional<Student>GetStByid(Long id);
}
