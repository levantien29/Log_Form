package com.example.Java_User.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Java_User.Model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
