package com.example.Rest_PerSon.Exception;
/*  BadRequestException được sử dụng để báo lỗi khi dữ liệu không hợp lệ (khongtim thay id..) 
hoặc yêu cầu sai từ phía client, trả về HTTP 400.*/
public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
