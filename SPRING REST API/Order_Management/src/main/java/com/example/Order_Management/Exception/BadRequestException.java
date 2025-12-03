package com.example.Order_Management.Exception;

/*
Dữ liệu gửi lên từ client bị sai
Yêu cầu không hợp lệ: trùng tên, trùng email, thiếu trường, định dạng sai
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
