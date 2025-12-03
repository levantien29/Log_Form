package com.example.User_Dto.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.User_Dto.Model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
