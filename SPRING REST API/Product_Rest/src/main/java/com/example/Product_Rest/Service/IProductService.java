package com.example.Product_Rest.Service;

import java.util.List;

import com.example.Product_Rest.Dto.ProductRequest;
import com.example.Product_Rest.Dto.ProductResponse;

public interface IProductService {
    public List<ProductResponse> getAll();
    public ProductResponse create(ProductRequest request);
    public ProductResponse getByid(Long id);
    public ProductResponse update(ProductRequest request, Long id);
    public void delete(Long id);
}
