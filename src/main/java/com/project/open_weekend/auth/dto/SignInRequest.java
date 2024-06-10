package com.project.open_weekend.auth.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
}
