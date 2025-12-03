package com.example.Product_Mapstruct.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.Product_Mapstruct.Dto.ProductRequest;
import com.example.Product_Mapstruct.Dto.ProductResponse;
import com.example.Product_Mapstruct.Service.IProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService service;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // danh sách phân trangg
    @GetMapping("/paged")
    public ResponseEntity<Page<ProductResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.getAll(pageable));
    }

    // getById
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        ProductResponse response = service.create(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@Valid @RequestBody ProductRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // get
    @GetMapping("/feauture")
    public ResponseEntity<Page<ProductResponse>> getFeautured(Pageable pageable) {
        return ResponseEntity.ok(service.findByFeaturedTrue(pageable));
    }

    // getName
    @GetMapping("/name")
    public ResponseEntity<Page<ProductResponse>> getName(@RequestParam String name, Pageable pageable) {
        return ResponseEntity.ok(service.findByNameIgnoreCase(name, pageable));
    }

    // getbrand
    @GetMapping("/brand")
    public ResponseEntity<Page<ProductResponse>> getBrand(@RequestParam String brand, Pageable pageable) {
        return ResponseEntity.ok(service.findByBrandIgnoreCase(brand, pageable));
    }
}
