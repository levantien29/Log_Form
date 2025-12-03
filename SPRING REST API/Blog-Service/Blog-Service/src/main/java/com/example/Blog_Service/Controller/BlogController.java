package com.example.Blog_Service.Controller;

import com.example.Blog_Service.Dto.BlogRequest;
import com.example.Blog_Service.Dto.BlogResponse;
import com.example.Blog_Service.Service.IBlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
@RequiredArgsConstructor
public class BlogController {
    private final IBlogService service;

    @GetMapping
    public ResponseEntity<List<BlogResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<BlogResponse>> getAll(Pageable pageable){
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<BlogResponse> create(@Valid @RequestBody BlogRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogResponse> update(@Valid @RequestBody BlogRequest request, @PathVariable Long id){
        return ResponseEntity.ok(service.update(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BlogResponse> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/title")
    public ResponseEntity<Page<BlogResponse>> getTitle(@RequestParam String title, Pageable pageable){
        return ResponseEntity.ok(service.findByTitle(title, pageable));
    }

    @GetMapping("/author")
    public ResponseEntity<Page<BlogResponse>> getAuthor(@RequestParam String author, Pageable pageable){
        return ResponseEntity.ok(service.findByAuthor(author, pageable));
    }

    @GetMapping("/catetory")
    public ResponseEntity<Page<BlogResponse>> getCatetory(@RequestParam String catetory, Pageable pageable){
        return ResponseEntity.ok(service.findByCatetory(catetory, pageable));
    }

    @GetMapping("/featured")
    public ResponseEntity<Page<BlogResponse>> getFeatured(Pageable pageable){
        return ResponseEntity.ok(service.findByFeatued(pageable));
    }

    @GetMapping("/price")
    public ResponseEntity<Page<BlogResponse>> getPrice(@RequestParam double min, @RequestParam double max, Pageable pageable){
        return ResponseEntity.ok(service.findByPrice(min, max, pageable));
    }
}
