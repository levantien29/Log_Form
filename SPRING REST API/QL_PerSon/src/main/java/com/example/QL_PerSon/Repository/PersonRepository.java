package com.example.QL_PerSon.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QL_PerSon.Model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
}
