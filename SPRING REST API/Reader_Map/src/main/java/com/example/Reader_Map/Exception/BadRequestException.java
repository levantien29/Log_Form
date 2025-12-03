package com.example.Reader_Map.Exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException{
    private final String field;

    public BadRequestException(String field, String message){
        super(message);
        this.field = field;
    }
}
