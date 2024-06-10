package com.project.open_weekend.user;

public interface UserService {

    User findUserByEmail(String email);

    User findUserById(long id);

    boolean isUserDoesNotExist(String email);

    void saveUser(User user);



}
