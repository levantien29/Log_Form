package com.example.Book_Rest.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Book_Rest.Dto.BookRequest;
import com.example.Book_Rest.Dto.BookResponse;
import com.example.Book_Rest.Exception.BadRequestException;
import com.example.Book_Rest.Exception.ResourceNotFoundException;
import com.example.Book_Rest.Mapper.BookMapper;
import com.example.Book_Rest.Model.Book;
import com.example.Book_Rest.Repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final BookRepository repository;

    public List<BookResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(BookMapper::toResponse)
                .collect(Collectors.toList());
    }

    public BookResponse getbyId(Long id) {
        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay sach co id = " + id));
        return BookMapper.toResponse(book);
    }

    public BookResponse create(BookRequest request) {
        if (repository.existsByTitle(request.getTitle())) {
            throw new BadRequestException("Ten da ton tai, Vui long nhap ten khac");
        }
        Book book = BookMapper.toBook(request);
        return BookMapper.toResponse(repository.save(book));
    }

    public BookResponse update(BookRequest request, Long id) {
        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay id"));
        if (repository.existsByTitleAndIdNot(request.getTitle(), id)) {
            throw new BadRequestException("Ten da ton tai");
        }
        BookMapper.update(book, request);
        return BookMapper.toResponse(repository.save(book));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Khoong tim thay id");
        }
        repository.deleteById(id);
    }
}
