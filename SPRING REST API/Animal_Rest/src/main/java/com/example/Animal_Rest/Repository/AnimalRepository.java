package com.example.Animal_Rest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Animal_Rest.Model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long>{
    boolean existsByName(String name); //kiểm tra tên đã tồn tại chưa
}
