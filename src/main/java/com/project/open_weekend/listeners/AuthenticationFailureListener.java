package com.project.open_weekend.listeners;

import com.project.open_weekend.auth.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthenticationFailureListener {

    @Autowired
    private final LoginAttemptService loginAttemptService;

    @EventListener
    public void onAuthenticationFailure(AuthenticationFailureBadCredentialsEvent event) {
        var principal = event.getAuthentication().getPrincipal();
        if (principal instanceof String) {
            loginAttemptService.addUserToLoginAttemptCache((String) principal);
        }
    }
}