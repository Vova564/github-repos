package com.example.githubrepos.api.error;

import com.example.githubrepos.api.error.dto.BadContentTypeExceptionResponseDTO;
import com.example.githubrepos.api.error.dto.ApiServiceExceptionResponseDTO;
import com.example.githubrepos.api.error.dto.UserNotFoundExceptionResponseDTO;
import com.example.githubrepos.api.error.exception.ApiServiceException;
import com.example.githubrepos.api.error.exception.BadContentTypeException;
import com.example.githubrepos.api.error.exception.UserNameNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class ErrorHandler {

    @ExceptionHandler(UserNameNotFoundException.class)
    public ResponseEntity<UserNotFoundExceptionResponseDTO> handleUserNameNotFoundException(UserNameNotFoundException exception) {
        log.warn("Caught UserNameNotFoundException: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserNotFoundExceptionResponseDTO(HttpStatus.NOT_FOUND, exception.getMessage()));
    }

    @ExceptionHandler(BadContentTypeException.class)
    public ResponseEntity<BadContentTypeExceptionResponseDTO> handleBadContentTypeException(BadContentTypeException exception) {
        log.warn("Caught BadContentTypeException: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new BadContentTypeExceptionResponseDTO(HttpStatus.NOT_ACCEPTABLE, exception.getMessage()));
    }

    @ExceptionHandler(ApiServiceException.class)
    public ResponseEntity<ApiServiceExceptionResponseDTO> handleApiServiceException(ApiServiceException exception) {
        log.warn("Caught ApiServiceException: {} - {}", exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiServiceExceptionResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()));
    }
}
