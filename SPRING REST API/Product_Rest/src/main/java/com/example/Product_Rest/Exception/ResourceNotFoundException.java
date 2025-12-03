package com.example.Product_Rest.Exception;

//ID không tồn tại trong DB, Truy vấn đối tượng không còn
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
