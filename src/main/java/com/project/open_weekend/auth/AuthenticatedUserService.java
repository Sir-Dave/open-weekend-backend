package com.project.open_weekend.auth;

import com.project.open_weekend.exception.EntityNotFoundException;
import com.project.open_weekend.user.User;
import com.project.open_weekend.user.UserRepository;
import com.project.open_weekend.util.Role;
import com.project.open_weekend.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticatedUserService {

    @Autowired
    private final UserRepository userRepository;

    public User getAuthenticatedUser(){
        var email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No user with email %s was found", email)
                ));
    }

    public boolean hasId(long userId){
        return getAuthenticatedUser().getId() == userId;
    }

    public boolean isAdminUser(){
        var user = getAuthenticatedUser();
        var role = Util.getEnumName(user.getRole(), Role.class);
        return role == Role.ROLE_ADMIN;
    }
}
