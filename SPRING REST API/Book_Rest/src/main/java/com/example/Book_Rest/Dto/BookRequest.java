package com.example.Book_Rest.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BookRequest {
    @NotBlank(message = "Tên sách không được để trống")
    private String title;

    @NotBlank(message = "Tác giả không được để trống")
    private String author;

    @NotBlank(message = "Thể loại không được để trống")
    private String category;

    @NotBlank(message = "Mã sách không được để trống")
    private String isbn;
}
