package com.example.Product_Mapstruct.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Product_Mapstruct.Dto.ProductRequest;
import com.example.Product_Mapstruct.Dto.ProductResponse;
import com.example.Product_Mapstruct.Exception.BadRequestException;
import com.example.Product_Mapstruct.Exception.ResourceNotFoundException;
import com.example.Product_Mapstruct.Mappper.ProductMapper;
import com.example.Product_Mapstruct.Model.Product;
import com.example.Product_Mapstruct.Repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    // all
    public List<ProductResponse> getAll() {
        return mapper.toList(repository.findAll());
    }

    // getAll phaan trng
    public Page<ProductResponse> getAll(Pageable pageable){
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    //getByid
    public ProductResponse getById(Long id){
        Product product = repository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Không tìm thấy sản phẩm có id = " + id));
        return mapper.toResponse(product);
    }

    //create
    public ProductResponse create(ProductRequest request){
        if (repository.existsByName(request.getName())) {
            throw new BadRequestException("Sản phẩm đã tồn tại");
        }
        Product  product = mapper.toProduct(request);
        return mapper.toResponse(product);
    }

    //update
    public ProductResponse update(ProductRequest request, Long id){
        Product product = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm có id = " + id));
        if (repository.existsByNameAndIdNot(request.getName(), id)) {
            throw new ResourceNotFoundException("Sản phẩm đã tồn tại");
        }
        mapper.toUpdate(product, request);
        return mapper.toResponse(product);
    }

    //xoá
    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Không tìm thấy người dùng có id = " + id);
        }
        repository.deleteById(id);
    }

    //nổi bật
    public Page<ProductResponse> findByFeaturedTrue(Pageable pageable){
        return repository.findByFeatureTrue(pageable).map(mapper::toResponse);
    }

    //tìm tên
    public Page<ProductResponse> findByNameIgnoreCase(String name, Pageable pageable){
        Page<Product> page = repository.findByNameIgnoreCase(name, pageable);
        if (page.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy sản phẩm có tên : " + name);
        }
        return page.map(mapper::toResponse);
    }

    //hãng
    public Page<ProductResponse> findByBrandIgnoreCase(String brand, Pageable pageable){
        Page<Product> page = repository.findByBrandIgnoreCase(brand, pageable);
        if (page.isEmpty()) {
            throw new ResourceNotFoundException("Khôn tìm thấy brand có tên : " + brand);
        }
        return page.map(mapper::toResponse);
    }
}
