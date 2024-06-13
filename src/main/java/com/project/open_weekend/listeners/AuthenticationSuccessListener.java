package com.project.open_weekend.listeners;

import com.project.open_weekend.auth.LoginAttemptService;
import com.project.open_weekend.user.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthenticationSuccessListener {

    @Autowired
    private final LoginAttemptService loginAttemptService;

    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event){
        var principal = event.getAuthentication().getPrincipal();
        if (principal instanceof UserPrincipal){
            loginAttemptService.evictUserFromLoginAttemptCache(((UserPrincipal) principal).getUsername());
        }
    }
}