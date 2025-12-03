package com.example.Book_Rest.Service;

import java.util.List;

import com.example.Book_Rest.Dto.BookRequest;
import com.example.Book_Rest.Dto.BookResponse;

public interface IBookService {
    List<BookResponse> getAll();
    public BookResponse getbyId(Long id);
    public BookResponse create(BookRequest request);
    public BookResponse update(BookRequest request, Long id);
    public void delete(Long id);
}
