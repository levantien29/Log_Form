package com.example.User_Rest.Exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class ErrorResponse {
    @Builder.Default
    private LocalDateTime localDateTime = LocalDateTime.now();
    private int status;
    private String message;
    private List<String> messages;
    private String path;
}
