package com.example.Blog_Service.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BlogResponse {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String catetory;
    private LocalDateTime createAt;
    private double price;
    private boolean featured;
}
