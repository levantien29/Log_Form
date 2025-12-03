package com.example.Book_Rest.Mapper;

import com.example.Book_Rest.Dto.BookRequest;
import com.example.Book_Rest.Dto.BookResponse;
import com.example.Book_Rest.Model.Book;

public class BookMapper {

    //theem
    public static Book toBook(BookRequest request){
        return new Book(null, request.getTitle(), request.getAuthor(), request.getCategory(), request.getIsbn());
    }

    //update
    public static void update(Book book, BookRequest request){
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setCategory(request.getCategory());
        book.setIsbn(request.getIsbn());
    }

    //toResponse
    public static BookResponse toResponse(Book book){
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getCategory());
    }
}
