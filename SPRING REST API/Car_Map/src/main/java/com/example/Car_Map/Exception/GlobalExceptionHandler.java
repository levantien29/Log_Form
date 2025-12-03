package com.example.Car_Map.Exception;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // valid : notBlank, min, max...
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValiDation(MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        List<ErrorDetail> errorDetails = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> ErrorDetail.builder()
                //Trả về tên field bị lỗi
                        .field(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .code(ErrorCode.VALIDATION_ERROR.name())
                        .build())
                .collect(Collectors.toList());
        ErrorResponse response = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .path(request.getRequestURI())
                .errors(errorDetails)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    // @RequestParam, @PathVariable, @RequestHeader
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex,
            HttpServletRequest request) {
        List<ErrorDetail> details = ex.getConstraintViolations()
                .stream()
                .map(violation -> ErrorDetail.builder()
                        // field lấy tên method và tham số
                        .field(violation.getPropertyPath().toString())
                        .message(violation.getMessage())
                        .code(ErrorCode.CONSTRAINT_VIOLATION.name())
                        .build())
                .toList();

        ErrorResponse errors = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .path(request.getRequestURI())
                .errors(details)
                .build();

        return ResponseEntity.badRequest().body(errors);
    }

    // 400, bắt giá trị đã tồn tại, tự định nghĩa
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handlerBadRequest(BadRequestException ex, HttpServletRequest request) {
        ErrorDetail detail = ErrorDetail.builder()
                .field(ex.getField())
                .message(ex.getMessage())
                .code(ErrorCode.BAD_REQUEST.name())
                .build();

        ErrorResponse response = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .path(request.getRequestURI())
                .errors(List.of(detail))
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    // 404 không tìm thấy dữ liệu id..
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        ErrorDetail detail = ErrorDetail.builder()
                .field(ex.getField())
                .message(ex.getMessage())
                .code(ErrorCode.RESOURCE_NOT_FOUND.name())
                .build();

        ErrorResponse errors = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not Found")
                .path(request.getRequestURI())
                .errors(List.of(detail))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    // 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request) {
        ErrorDetail detail = ErrorDetail.builder()
                .field(null)
                .message(ex.getMessage())
                .code(ErrorCode.INTERNAL_ERROR.name())
                .build();

        ErrorResponse errors = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .path(request.getRequestURI())
                .errors(List.of(detail))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
    }
}
