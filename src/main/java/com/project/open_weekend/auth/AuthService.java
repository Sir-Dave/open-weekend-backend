package com.project.open_weekend.auth;

import com.project.open_weekend.auth.dto.RegisterRequest;
import com.project.open_weekend.auth.dto.SignInRequest;
import com.project.open_weekend.auth.dto.SignInResponse;
import com.project.open_weekend.exception.EntityExistsException;
import com.project.open_weekend.exception.EntityNotFoundException;
import com.project.open_weekend.exception.PasswordsDoNotMatchException;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    void register(RegisterRequest registerRequest) throws EntityExistsException, PasswordsDoNotMatchException;
    SignInResponse loginUser(SignInRequest signInRequest) throws EntityNotFoundException;


}
