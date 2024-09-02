package com.example.githubrepos.api.error.exception;

public class RecordsNotFoundException extends RuntimeException {
    public RecordsNotFoundException(String message) {
        super(message);
    }
}
