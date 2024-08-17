package com.example.githubrepos.api.error.exception;

public class ApiServiceException extends RuntimeException {
    public ApiServiceException(String exception) {
        super(exception);
    }
}
