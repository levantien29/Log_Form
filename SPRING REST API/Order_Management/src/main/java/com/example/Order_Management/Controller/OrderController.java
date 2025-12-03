package com.example.Order_Management.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Order_Management.Dto.OderResponse;
import com.example.Order_Management.Dto.OrderRequest;
import com.example.Order_Management.Service.IOderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/order")
@RestController
@RequiredArgsConstructor
public class OrderController {
    private final IOderService service;

    @GetMapping
    public List<OderResponse> getAll() {
        return service.getAll();
    }

    // lấy theo id
    @GetMapping("/{id}")
    public OderResponse getById(@PathVariable Long id){
        return service.getById(id);
    }

    // thêm
    @PostMapping
    public OderResponse create(@Valid @RequestBody OrderRequest request) {
        return service.create(request);
    }

    // sửa
    @PutMapping("/{id}")
    public OderResponse update(@Valid @RequestBody OrderRequest request, @PathVariable Long id) {
        return service.update(request, id);
    }

    // xoá
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
