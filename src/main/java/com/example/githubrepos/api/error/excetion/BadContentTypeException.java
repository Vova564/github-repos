package com.example.githubrepos.api.error.excetion;

public class BadContentTypeException extends RuntimeException {
    public BadContentTypeException(String message) {
        super(message);
    }
}
