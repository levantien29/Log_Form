package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Quan_Li_Nhan_Su.Model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
