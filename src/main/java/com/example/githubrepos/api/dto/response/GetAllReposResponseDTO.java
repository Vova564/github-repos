package com.example.githubrepos.api.dto.response;

import java.util.List;

public record GetAllReposResponseDTO(List<RepoDTO> repos) {
}
