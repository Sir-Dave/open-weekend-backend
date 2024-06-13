package com.project.open_weekend.auth;

import com.project.open_weekend.auth.dto.RegisterRequest;
import com.project.open_weekend.auth.dto.SignInRequest;
import com.project.open_weekend.auth.dto.SignInResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    void register(RegisterRequest registerRequest);
    ResponseEntity<SignInResponse> loginUser(SignInRequest signInRequest);


}
