package com.example.githubrepos.api.dto.request;

import jakarta.validation.constraints.NotNull;

public record CreateRepoRequestDTO(
        @NotNull(message = "Owner must be filed")
        String owner,

        @NotNull(message = "Name must be filed")
        String name
) { }
