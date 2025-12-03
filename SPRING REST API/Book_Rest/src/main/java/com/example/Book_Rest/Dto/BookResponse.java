package com.example.Book_Rest.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private String category;
}
