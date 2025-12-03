package com.example.Demo_DTO.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Demo_DTO.Model.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long>{

}
