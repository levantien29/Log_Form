package com.example.Product_Mapstruct.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.Product_Mapstruct.Dto.ProductRequest;
import com.example.Product_Mapstruct.Dto.ProductResponse;
import com.example.Product_Mapstruct.Model.Product;

public interface IProductService {
    public List<ProductResponse> getAll();

    Page<ProductResponse> getAll(Pageable pageable);

    public ProductResponse getById(Long id);

    public ProductResponse create(ProductRequest request);

    public ProductResponse update(ProductRequest request, Long id);

    public void delete(Long id);

    Page<ProductResponse> findByFeaturedTrue(Pageable pageable);

    Page<ProductResponse> findByNameIgnoreCase(String name, Pageable pageable);

    Page<ProductResponse> findByBrandIgnoreCase(String Brand, Pageable pageable);
}
