package com.example.Book_Rest.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Book_Rest.Dto.BookRequest;
import com.example.Book_Rest.Dto.BookResponse;
import com.example.Book_Rest.Service.IBookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {
    private final IBookService service;

    @GetMapping
    public List<BookResponse> getAll(){
        return service.getAll();
    }

    @PostMapping
    public BookResponse create(BookRequest request){
        return service.create(request);
    }

    @PutMapping("/{id}")
    public BookResponse update(@Valid @RequestBody BookRequest request, @PathVariable Long id){
        return service.update(request, id);
    }

    @GetMapping("/{id}")
    public BookResponse getbyId(@PathVariable Long id){
        return service.getbyId(id);
    }

    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
