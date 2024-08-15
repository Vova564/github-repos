package com.example.githubrepos.client.dto;

import com.example.githubrepos.client.model.OwnerModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubRepositoryResponseDTO(String name, OwnerModel owner) {
}
