package com.example.Order_Management.Exception;

/*
Client yêu cầu một tài nguyên không tồn tại trên hệ thống
ID truyền vào không tìm thấy trong DB
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
