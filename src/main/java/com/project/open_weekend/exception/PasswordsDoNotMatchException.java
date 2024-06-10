package com.project.open_weekend.exception;

public class PasswordsDoNotMatchException extends RuntimeException {

    public PasswordsDoNotMatchException(String message){
        super(message);
    }
}
