package com.example.Rest_PerSon.Exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL) // ẩn trường null
@Getter
public class ErrorrResponse {
    private LocalDateTime localDateTime;
    private int status;
    private String message;
    private List<String> messages;
    private String path;


    public ErrorrResponse(int status, String message, String path){
        this.localDateTime = localDateTime.now();
        this.status = status;
        this.message = message;
        this.path = path;
    }
    public ErrorrResponse(int status, List<String> messages, String path){
        this.localDateTime = localDateTime.now();
        this.status = status;
        this.messages = messages;
        this.path = path;
    }
}
