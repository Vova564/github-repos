package com.example.githubrepos.api.dto.response;

import com.example.githubrepos.api.model.BranchModel;

import java.util.List;

public record GitHubRepositoriesResponseDTO(String repo, String owner, List<BranchModel> branches) {
}
