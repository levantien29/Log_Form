package com.example.Auth_API.Dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
