package com.example.githubrepos.client;

import com.example.githubrepos.client.dto.GitHubBranchesResponseDTO;
import com.example.githubrepos.client.dto.GitHubRepositoryResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "github", url = "${github.api.host}")
public interface GitHubApiClient {

    @GetMapping(value = "users/{username}/repos")
    List<GitHubRepositoryResponseDTO> getRepositories(@PathVariable String username);

    @GetMapping(value = "repos/{username}/{repo}/branches")
    List<GitHubBranchesResponseDTO> getBranches(@PathVariable String username, @PathVariable String repo);

}
