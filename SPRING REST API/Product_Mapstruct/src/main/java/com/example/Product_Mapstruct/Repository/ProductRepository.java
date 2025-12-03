package com.example.Product_Mapstruct.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Product_Mapstruct.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    Page<Product> findByFeatureTrue(Pageable pageable);

    Page<Product> findByNameIgnoreCase(String name, Pageable pageable);

    Page<Product> findByBrandIgnoreCase(String brand, Pageable pageable);
}
