package com.example.githubrepos.api.service;

import com.example.githubrepos.api.error.exception.ApiServiceException;
import com.example.githubrepos.api.error.exception.UserNameNotFoundException;
import com.example.githubrepos.api.dto.response.GitHubRepositoriesResponseDTO;
import com.example.githubrepos.api.model.BranchModel;
import com.example.githubrepos.client.GitHubApiClient;
import com.example.githubrepos.client.dto.GitHubRepositoryResponseDTO;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RepositoriesService {

    private final GitHubApiClient gitHubApiClient;

    public List<GitHubRepositoriesResponseDTO> getUserRepos(String username) {
        try {
            List<GitHubRepositoryResponseDTO> repos = gitHubApiClient.getRepositories(username);
            List<GitHubRepositoriesResponseDTO> gitHubRepositoriesResponseDTO = new ArrayList<>();

            repos.forEach(repo -> {
                List<BranchModel> branchNames = gitHubApiClient.getBranches(username, repo.name())
                        .stream()
                        .map(branch -> new BranchModel(branch.name(), branch.commit().sha()))
                        .toList();

                gitHubRepositoriesResponseDTO.add(new GitHubRepositoriesResponseDTO(
                        repo.name(),
                        repo.owner().login(),
                        branchNames
                ));
            });

            return gitHubRepositoriesResponseDTO;
        } catch (FeignException.FeignClientException exception) {
            throw new UserNameNotFoundException("User with name " + username + " not found");
        } catch (FeignException exception) {
            throw new ApiServiceException("An error occurred while processing the request. Please try again later");
        }
    }
}
