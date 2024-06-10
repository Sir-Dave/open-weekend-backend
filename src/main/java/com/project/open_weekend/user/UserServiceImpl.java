package com.project.open_weekend.user;

import com.project.open_weekend.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Qualifier("userDetailsService")
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepository userRepository;
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No user with email %s was found", email)
                ));
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No user with id %s was found", id)
                ));
    }

    @Override
    public boolean isUserDoesNotExist(String email) {
        var userByEmail = userRepository.findUserByEmail(email);
        return userByEmail.isEmpty();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
