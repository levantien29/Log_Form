package com.example.Book_Rest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Book_Rest.Model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
    boolean existsByTitle(String title);
    boolean existsByTitleAndIdNot(String title, Long id);
}
