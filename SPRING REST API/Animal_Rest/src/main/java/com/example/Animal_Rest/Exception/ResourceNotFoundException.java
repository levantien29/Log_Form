package com.example.Animal_Rest.Exception;

//Khi người dùng yêu cầu một tài nguyên (ví dụ: Animal, User, Product) 
//không tồn tại trong cơ sở dữ liệu → bạn ném lỗi này để thông báo là "không tìm thấy".

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
