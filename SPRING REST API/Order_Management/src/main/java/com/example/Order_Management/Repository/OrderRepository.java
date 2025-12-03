package com.example.Order_Management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Order_Management.Model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    boolean existsByName(String name);
    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);
    boolean existsByNameAndIdNot(String name, Long id);
}
