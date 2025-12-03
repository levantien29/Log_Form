package com.example.Quan_Li_Nhan_Su.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Repository.StudentRepository;
import com.example.Quan_Li_Nhan_Su.Model.Student;

//đánh dấu class là một bean thuộc tầng service
@Service
public class StudentService implements IStudentService {

    private final StudentRepository studentrepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentrepository = studentRepository;
    }

    // lấy toàn bộ sinh viên
    @Override
    public List<Student> getAllStudent() {
        return studentrepository.findAll();
    }

    // thêm sinh viên
    @Override
    public Student add(Student student) {
        return studentrepository.save(student);
    }

    // cập nhập sinh viên
    @Override
    public Student update(Long id, Student student) {
        Optional<Student> optional = studentrepository.findById(id);
        if (optional.isPresent()) {
            Student st = optional.get();
            st.setName(student.getName());
            st.setEmail(student.getEmail());
            st.setAddress(student.getAddress());
            return studentrepository.save(st);
        } else {
            throw new RuntimeException("khong tim thay sv voi id" + id);

        }
    }

    // xoá theo id
    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    // tìm theo id
    @Override
    public Optional<Student> GetStByid(Long id) {
        return studentRepository.findById(id);
    }
}
