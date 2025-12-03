package com.example.Product_Rest.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Product_Rest.Dto.ProductRequest;
import com.example.Product_Rest.Dto.ProductResponse;
import com.example.Product_Rest.Exception.BadRequestException;
import com.example.Product_Rest.Exception.ResourceNotFoundException;
import com.example.Product_Rest.Mapper.ProductMapper;
import com.example.Product_Rest.Model.Product;
import com.example.Product_Rest.Repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    private final ProductRepository repository;

    public List<ProductResponse> getAll(){
        return repository.findAll()
        .stream()
        .map(ProductMapper::toResponse)
        .collect(Collectors.toList());
    }

    public ProductResponse getByid(Long id){
        Product product = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy product có id = " + id));
        return ProductMapper.toResponse(product);
    }

    public ProductResponse create(ProductRequest request){
        if (repository.existsByName(request.getName())) {
            throw new BadRequestException("Tên đã tồn tại");
        }
        Product product = ProductMapper.toProduct(request);
        return ProductMapper.toResponse(repository.save(product));
    }

    public ProductResponse update(ProductRequest request, Long id){
        Product product2 = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Product có id = " + id));
        ProductMapper.toUpdate(product2, request);
        return ProductMapper.toResponse(product2);
    }
    
    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Không tìm thấy Product có id = " + id);
        }
        repository.deleteById(id);
    }
}
