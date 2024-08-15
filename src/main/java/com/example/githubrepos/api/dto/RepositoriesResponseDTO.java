package com.example.githubrepos.api.dto;

import com.example.githubrepos.api.model.BranchModel;

import java.util.List;

public record RepositoriesResponseDTO(String repo, String owner, List<BranchModel> branches) {
}
