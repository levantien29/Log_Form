package com.example.Rest_PerSon.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Rest_PerSon.Model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
    boolean existsByName(String name);
}
