package com.example.githubrepos.api.error.excetion;

public class UserNameNotFoundException extends RuntimeException {
    public UserNameNotFoundException(String exception) {
        super(exception);
    }
}
