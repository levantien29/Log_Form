package com.example.Reader_Map.Exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException{
    private final String field;

    public ResourceNotFoundException(String field, String message){
        super(message);
        this.field = field;
    }
}
