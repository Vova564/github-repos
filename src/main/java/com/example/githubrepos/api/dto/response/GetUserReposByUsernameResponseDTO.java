package com.example.githubrepos.api.dto.response;

import java.util.List;

public record GetUserReposByUsernameResponseDTO(List<RepoDTO> repos) {
}
