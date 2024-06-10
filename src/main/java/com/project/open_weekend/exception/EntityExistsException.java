package com.project.open_weekend.exception;

public class EntityExistsException extends RuntimeException{

    public EntityExistsException(String message){
        super(message);
    }
}
