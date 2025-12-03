package com.example.Rest_PerSon.Exception;
/*  ResourceNotFoundException được sử dụng để báo lỗi khi không tìm thấy tài nguyên (kiem tra xem co ton tai chua), 
thường trả về HTTP 404.*/
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
