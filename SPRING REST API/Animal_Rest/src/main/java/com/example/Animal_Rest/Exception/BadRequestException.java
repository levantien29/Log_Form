package com.example.Animal_Rest.Exception;

//Khi dữ liệu người dùng gửi lên không hợp lệ 
//(ví dụ: trùng tên, sai logic), bạn ném BadRequestException để báo lỗi có ý nghĩa.

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
