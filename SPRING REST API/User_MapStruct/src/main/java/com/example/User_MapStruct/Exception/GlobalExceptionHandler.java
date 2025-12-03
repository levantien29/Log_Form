package com.example.User_MapStruct.Exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // @valid (post, put)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        ErrorResponse error = ErrorResponse.builder()
                .localDateTime(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .messages(errors)
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(error);
    }

    // @RequestParam, @PathVariable validation
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex,
            HttpServletRequest request) {
        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        ErrorResponse error = ErrorResponse.builder()
                .localDateTime(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .messages(errors)
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(error);
    }

    //404 không tìm thấy tài nguyên id, name, email
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request){
        ErrorResponse error = ErrorResponse.builder()
        .localDateTime(LocalDateTime.now())
        .status(HttpStatus.NOT_FOUND.value())
        .messages(List.of(ex.getMessage()))
        .path(request.getRequestURI())
        .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    //400
    //valid đầu vào sai : tên, email..
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex, HttpServletRequest  request){
        ErrorResponse error = ErrorResponse.builder()
        .localDateTime(LocalDateTime.now())
        .status(HttpStatus.BAD_REQUEST.value())
        .messages(List.of(ex.getMessage()))
        .path(request.getRequestURI())
        .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    //500: Lỗi trong xử lý của server
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOtherErrors(Exception ex, HttpServletRequest request){
        ErrorResponse error = ErrorResponse.builder()
        .localDateTime(LocalDateTime.now())
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .messages(List.of("Lỗi hệ thống : " + ex.getMessage()))
        .path(request.getRequestURI())
        .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
