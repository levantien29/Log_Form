package com.example.Reader_Map.Exception;

import java.time.Instant;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {
    private Instant timestamp;
    private int status;
    private String error;
    private String path;
    private List<ErrorDetail> errors;
}
