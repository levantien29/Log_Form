package com.example.Animal_Rest.Exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

//Tạo mẫu JSON phản hồi lỗi chuẩn, dễ đọc, dễ debug, và hỗ trợ frontend xử lý tốt hơn.

@JsonInclude(JsonInclude.Include.NON_NULL) // không hiển thị trường null
@Getter
public class ErrorResponse {
    private LocalDateTime localDateTime;
    private int status;
    private String message;
    private List<String> messages;
    private String path;

    public ErrorResponse(int status, String message, String path){
        this.localDateTime = localDateTime.now();
        this.status =  status;
        this.message = message;
        this.path = path;
    }
    public ErrorResponse(int status, List<String> messages, String path){
        this.localDateTime = localDateTime.now();
        this.status =  status;
        this.messages = messages;
        this.path = path;
    }
}
