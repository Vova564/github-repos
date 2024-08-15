package com.example.githubrepos.api.error.dto;

import org.springframework.http.HttpStatus;

public record UserNotFoundExceptionResponseDTO(HttpStatus status, String message) {
}
