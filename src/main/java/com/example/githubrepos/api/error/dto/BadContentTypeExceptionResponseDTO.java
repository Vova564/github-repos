package com.example.githubrepos.api.error.dto;

import org.springframework.http.HttpStatus;

public record BadContentTypeExceptionResponseDTO(HttpStatus status, String message) {
}
