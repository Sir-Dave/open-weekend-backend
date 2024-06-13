package com.project.open_weekend.auth.dto;

import com.project.open_weekend.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInResponse {
    private String token;
    private UserDto user;


}
