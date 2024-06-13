package com.project.open_weekend.auth;

import com.project.open_weekend.auth.dto.RegisterRequest;
import com.project.open_weekend.auth.dto.SignInRequest;
import com.project.open_weekend.auth.dto.SignInResponse;
import com.project.open_weekend.util.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = {"api/v1/auth"})
public class AuthController {

    @Autowired
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<HttpResponse> registerUser(
            @RequestBody RegisterRequest registerRequest
    ){
        authService.register(registerRequest);
        var response = new HttpResponse(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED,
                HttpStatus.CREATED.getReasonPhrase(),
                "User registered successfully."
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<SignInResponse> loginUser(
            @RequestBody SignInRequest signInRequest) {
        var response = authService.loginUser(signInRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}