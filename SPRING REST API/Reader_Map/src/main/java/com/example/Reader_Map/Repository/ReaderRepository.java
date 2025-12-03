package com.example.Reader_Map.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.Reader_Map.Model.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Long>, JpaSpecificationExecutor<Reader> {
    boolean existsByName(String name);

    boolean existsByEmail(String email);

    boolean existsByNameAndIdNot(String name, Long id);

    boolean existsByEmailAndIdNot(String email, Long id);
}   