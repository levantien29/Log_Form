package com.example.Product_Rest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Product_Rest.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    boolean existsByName(String name);
}
