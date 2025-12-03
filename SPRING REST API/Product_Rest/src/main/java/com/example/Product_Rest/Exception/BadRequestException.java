package com.example.Product_Rest.Exception;

//dl khoong hop le :Trùng tên,Nhập số âm,Gửi request không phù hợp với quy tắc bạn đặt ra.
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
