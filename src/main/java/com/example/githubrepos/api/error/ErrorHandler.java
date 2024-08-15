package com.example.githubrepos.api.error;

import com.example.githubrepos.api.error.dto.BadContentTypeExceptionResponseDTO;
import com.example.githubrepos.api.error.dto.UserNotFoundExceptionResponseDTO;
import com.example.githubrepos.api.error.excetion.BadContentTypeException;
import com.example.githubrepos.api.error.excetion.UserNameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(UserNameNotFoundException.class)
    public ResponseEntity<UserNotFoundExceptionResponseDTO> userNameNotFound(UserNameNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserNotFoundExceptionResponseDTO(HttpStatus.NOT_FOUND, exception.getMessage()));
    }

    @ExceptionHandler(BadContentTypeException.class)
    public ResponseEntity<BadContentTypeExceptionResponseDTO> userNameNotFound(BadContentTypeException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BadContentTypeExceptionResponseDTO(HttpStatus.NOT_FOUND, exception.getMessage()));
    }
}
