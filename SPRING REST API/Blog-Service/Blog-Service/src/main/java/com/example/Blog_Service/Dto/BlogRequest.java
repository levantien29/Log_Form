package com.example.Blog_Service.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Builder
public class BlogRequest {
    @NotBlank(message = "Tiêu đề không được để trống")
    private String title;

    @NotBlank(message = "Nội dung không được để trống")
    private String content;

    @NotBlank(message = "Tác giả không được để trống")
    private String author;

    @NotBlank(message = "Danh mục không được để trống")
    private String catetory;

    @NotNull(message = "Ngày xuất bản không được để trống")
    private LocalDateTime createAt;

    @NotNull(message = "Giá tiền không được để trống")
    @Min(value = 1, message = "Giá tiền không nhỏ hơn 1")
    private Double price;

    @NotNull(message = "Nổi bật không được để trống")
    private boolean featured;
}
