package com.example.quan_li_sinh_vien.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quan_li_sinh_vien.Model.Student;
import com.example.quan_li_sinh_vien.Repository.StudentRepository;

@RestController
@RequestMapping("/student")
// @RequestMapping là một annotation trong Spring Framework được sử dụng để ánh
// xạ các yêu cầu HTTP đến các phương thức trong controller.
public class StudentController {
    private final StudentRepository studentRepository;

    // Tiêm StudentRepository vào controller qua constructor
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // lay danh sach
    @GetMapping
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    // them sinh vien
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // xoa sinh vien
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }
}