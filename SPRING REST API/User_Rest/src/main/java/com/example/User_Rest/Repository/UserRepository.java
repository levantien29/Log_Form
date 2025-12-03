package com.example.User_Rest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.User_Rest.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByNameAndIdNot(String name, Long id);
    boolean existsByEmailAndIdNot(String email, Long id);
    
    boolean existsByName(String name);
    boolean existsByEmail(String email);

}
