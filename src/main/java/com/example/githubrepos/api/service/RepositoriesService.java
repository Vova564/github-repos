package com.example.githubrepos.api.service;

import com.example.githubrepos.api.error.excetion.UserNameNotFoundException;
import com.example.githubrepos.api.dto.RepositoriesResponseDTO;
import com.example.githubrepos.api.model.BranchModel;
import com.example.githubrepos.client.GitHubApiClient;
import com.example.githubrepos.client.dto.GitHubRepositoryResponseDTO;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Service
@Log4j2
public class RepositoriesService {

    private final GitHubApiClient gitHubApiClient;

    public RepositoriesService(GitHubApiClient gitHubApiClient) {
        this.gitHubApiClient = gitHubApiClient;
    }

    public List<RepositoriesResponseDTO> getUserRepos(String username) {
        try {
            List<GitHubRepositoryResponseDTO> repos = gitHubApiClient.getRepositories(username);
            List<RepositoriesResponseDTO> repositoriesResponseDTO = new ArrayList<>();

            repos.forEach(repo -> {
                List<BranchModel> branchNames = gitHubApiClient.getBranches(username, repo.name())
                        .stream()
                        .map(branch -> new BranchModel(branch.name(), branch.commit().sha()))
                        .collect(Collectors.toList());

                repositoriesResponseDTO.add(new RepositoriesResponseDTO(
                        repo.name(),
                        repo.owner().login(),
                        branchNames
                ));
            });

            return repositoriesResponseDTO;
        } catch (FeignException.FeignClientException exception) {
            if (exception.status() == 404) {
                throw new UserNameNotFoundException("User with name " + username + " does not exist");
            }
            log.error("{} - {}", exception.status(), exception.getMessage());
            return emptyList();
        }
    }
}
