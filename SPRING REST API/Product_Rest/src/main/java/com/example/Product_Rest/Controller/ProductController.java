package com.example.Product_Rest.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Product_Rest.Dto.ProductRequest;
import com.example.Product_Rest.Dto.ProductResponse;
import com.example.Product_Rest.Service.IProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService iservice;

    @GetMapping
    public List<ProductResponse> getAll(){
        return iservice.getAll();
    }

    @GetMapping("/{id}")
    public ProductResponse getByid(@PathVariable Long id){
        return iservice.getByid(id);
    }

    @PostMapping
    public ProductResponse create(@Valid @RequestBody ProductRequest request){
        return iservice.create(request);
    }

    @PutMapping("/{id}")
    public ProductResponse update(@Valid @RequestBody ProductRequest request, @PathVariable Long id){
        return iservice.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        iservice.delete(id);
    }
}
