package com.example.githubrepos.client.dto;

import com.example.githubrepos.client.model.CommitModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubBranchesResponseDTO(String name, CommitModel commit) {
}
