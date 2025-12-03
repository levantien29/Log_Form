package com.example.Phone_MapStruct.Controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

import com.example.Phone_MapStruct.Dto.PhoneRequest;
import com.example.Phone_MapStruct.Dto.PhoneResponse;
import com.example.Phone_MapStruct.Service.IPhoneService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/phone")
public class PhoneController {
    private final IPhoneService service;


    // ResponseEntity<T> : T là kiểu dữ liệu muốn trả về
    // ResponseEntity Trả status code (như 200 OK, 404 NOT FOUND)
    // ds phan trang
    @GetMapping
    public ResponseEntity<Page<PhoneResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.getAll(pageable));
    }

    // da khoong phan trang
    @GetMapping("/all")
    public ResponseEntity<List<PhoneResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // lay theo id
    @GetMapping("/{id}")
    public ResponseEntity<PhoneResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<PhoneResponse> create(@Valid @RequestBody PhoneRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<PhoneResponse> update(@Valid @RequestBody PhoneRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PhoneResponse> delete(@PathVariable Long id) {
        service.delete(id);
        // Trả về HTTP status 204 No Content
        // build không trả về body
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PhoneResponse>> search(@RequestParam String name, @RequestParam double min,
            @RequestParam double max, Pageable pageable) {
        return ResponseEntity.ok(service.searchByNameAndPrice(name, min, max, pageable));
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<PhoneResponse>> filter(@RequestParam String brand, @RequestParam double min,
            @RequestParam double max, Pageable pageable) {
                return ResponseEntity.ok(service.filterByBrandAndPrice(brand, min, max, pageable));
    }

    @GetMapping("/featured")
    public ResponseEntity<Page<PhoneResponse>> getFeatured(Pageable pageable){
        return ResponseEntity.ok(service.getFeatured(pageable));
    }
}
