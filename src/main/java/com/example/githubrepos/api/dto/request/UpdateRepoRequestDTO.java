package com.example.githubrepos.api.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdateRepoRequestDTO(
        @NotNull(message = "Owner must be filed")
        String owner,

        @NotNull(message = "Name must be filed")
        String name
) { }
