package com.example.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Quan_Li_Nhan_Su.Model.Student;
import com.example.Quan_Li_Nhan_Su.Service.IStudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final IStudentService isstudentService;

    public StudentController(IStudentService isService) {
        this.isstudentService = isService;
    }

    @GetMapping
    public List<Student> getAllStudent() {
        return isstudentService.getAllStudent();
    }

    @GetMapping("/{id}")
    public Optional<Student> GetStByid(@PathVariable Long id) {
        return isstudentService.GetStByid(id);
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return isstudentService.add(student);
    }

    @PutMapping
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        return isstudentService.update(id, student);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        isstudentService.delete(id);
    }

}
