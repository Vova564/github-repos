package com.example.githubrepos.api.error.dto;

import org.springframework.http.HttpStatus;

public record RecordsNotFoundExceptionResponseDTO(HttpStatus status, String message) {
}
