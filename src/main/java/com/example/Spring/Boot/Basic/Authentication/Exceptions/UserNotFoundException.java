package com.example.Spring.Boot.Basic.Authentication.Exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message) ;
    }
}
