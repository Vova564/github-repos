package com.example.githubrepos.api.error.dto;

import org.springframework.http.HttpStatus;

public record ApiServiceExceptionResponseDTO(HttpStatus status, String message) {
}
