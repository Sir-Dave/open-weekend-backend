package com.project.open_weekend.auth;

import com.project.open_weekend.auth.dto.RegisterRequest;
import com.project.open_weekend.auth.dto.SignInRequest;
import com.project.open_weekend.auth.dto.SignInResponse;
import com.project.open_weekend.exception.EntityExistsException;
import com.project.open_weekend.exception.PasswordsDoNotMatchException;
import com.project.open_weekend.mapper.UserMapper;
import com.project.open_weekend.security.JwtTokenProvider;
import com.project.open_weekend.user.User;
import com.project.open_weekend.user.UserPrincipal;
import com.project.open_weekend.user.UserService;
import com.project.open_weekend.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.project.open_weekend.util.Util.getEnumName;

@RequiredArgsConstructor
@Service
@Qualifier("userDetailsService")
public class AuthServiceImpl implements AuthService, UserDetailsService {

    @Autowired
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findUserByEmail(username);
        userService.saveUser(user);
        return new UserPrincipal(user);
    }

    private void doPasswordsMatch(String p1 , String p2){
        if (!p1.equals(p2)){
            throw new PasswordsDoNotMatchException("Passwords do not match");
        }
    }

    private void validateNewUser(String email){
        if (!userService.isUserDoesNotExist(email)){
            throw new EntityExistsException(
                    String.format("User with email %s already exists", email)
            );
        }
    }

    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    @Override
    public void register(RegisterRequest registerRequest) {
        validateNewUser(registerRequest.getEmail());
        doPasswordsMatch(registerRequest.getPassword(), registerRequest.getConfirmPassword());

        var encodedPassword = encodePassword(registerRequest.getPassword());
        var role = getEnumName(registerRequest.getRole(), Role.class);
        var user = new User(
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getEmail(),
                registerRequest.getPhoneNumber(),
                encodedPassword,
                LocalDateTime.now(),
                role.name(),
                role.getAuthorities(),
                true,
                true
        );

        userService.saveUser(user);
    }

    @Override
    public ResponseEntity<SignInResponse> loginUser(SignInRequest signInRequest) {
        authenticateUser(signInRequest.getEmail(), signInRequest.getPassword());
        User user = userService.findUserByEmail(signInRequest.getEmail());

        var userPrincipal = new UserPrincipal(user);
        String token = jwtTokenProvider.generateJwtToken(userPrincipal);
        var response = new SignInResponse(token, UserMapper.mapUserToUserDto(user));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticateUser(String email, String password){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
    }


}
