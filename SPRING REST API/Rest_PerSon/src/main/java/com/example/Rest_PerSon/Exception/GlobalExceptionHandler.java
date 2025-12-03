package com.example.Rest_PerSon.Exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
//404 : khong tim thay tai nguyen
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorrResponse> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorrResponse(404, ex.getMessage(), request.getRequestURI()));
    }


    //400 : kiểm tra đã tồn tại hay chưa
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorrResponse> handleBadRequest(BadRequestException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ErrorrResponse(400, ex.getMessage(), request.getRequestURI()));
    }

    //400 lỗi validation @valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorrResponse> handValidation(MethodArgumentNotValidException ex, HttpServletRequest request){
        List<String> errors = ex.getBindingResult().getFieldErrors()
        .stream()
        .map(field -> field.getDefaultMessage())
        .collect(Collectors.toList());
        return ResponseEntity.badRequest()
        .body(new ErrorrResponse(400, errors, request.getRequestURI()));
    }
}
