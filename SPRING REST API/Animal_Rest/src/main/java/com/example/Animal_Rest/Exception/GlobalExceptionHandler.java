package com.example.Animal_Rest.Exception;

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

    //khong tim thay tai nguyen()
    //Xử lý lỗi khi không tìm thấy dữ liệu theo id hoặc key nào đó.
    @ExceptionHandler(ResourceNotFoundException.class) // khi gặp lỗi Réource thì spring chạy hàm này
    public ResponseEntity<ErrorResponse> handNotFound(ResourceNotFoundException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorResponse(404, ex.getMessage(), request.getRequestURI()));
    }

    //kiem tra da ton tai chua(ten, email, dia chi...)
    //Xử lý lỗi logic nghiệp vụ, ví dụ: tên đã tồn tại, email bị trùng, sai định dạng
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex, HttpServletRequest request){
        return ResponseEntity.status((HttpStatus.BAD_REQUEST))
        .body(new ErrorResponse(400, ex.getMessage(), request.getRequestURI()));
    }

    //loi validtion
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handValidtion(MethodArgumentNotValidException ex, HttpServletRequest request){
        List<String> errors = ex.getBindingResult().getFieldErrors()
        .stream()
        .map(field -> field.getDefaultMessage())
        .collect(Collectors.toList());
        return ResponseEntity.badRequest()
        .body(new ErrorResponse(400, errors, request.getRequestURI()));
    }
}
