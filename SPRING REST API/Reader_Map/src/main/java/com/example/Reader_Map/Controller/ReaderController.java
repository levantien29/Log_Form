package com.example.Reader_Map.Controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.Reader_Map.DTO.ReaderRequest;
import com.example.Reader_Map.DTO.ReaderResponse;
import com.example.Reader_Map.DTO.ReaderSearchRequest;
import com.example.Reader_Map.Service.IReaderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reader")
@RequiredArgsConstructor
public class ReaderController {
    private final IReaderService service;

    @GetMapping
    public ResponseEntity<Page<ReaderResponse>> getAll(Pageable pageable){
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReaderResponse> getById(Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ReaderResponse> create(@Valid @RequestBody ReaderRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReaderResponse> update(@Valid @RequestBody ReaderRequest request, @PathVariable Long id){
        return ResponseEntity.ok(service.update(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReaderResponse> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{search}")
    public ResponseEntity <Page<ReaderResponse>> searchReader(@RequestBody ReaderSearchRequest request, Pageable pageable){
        return ResponseEntity.ok(service.searchReader(request, pageable));
    }
}
