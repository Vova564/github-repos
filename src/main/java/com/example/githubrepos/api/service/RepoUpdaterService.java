package com.example.githubrepos.api.service;

import com.example.githubrepos.database.entity.Repo;
import com.example.githubrepos.database.repository.RepoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class RepoUpdaterService {
    private final RepoRepository repoRepository;
    private final RepoRetrieverService repoRetrieverService;

    public void updateRepoById(Long id, Repo repo) {
        repoRetrieverService.repoExistsById(id);
        repoRepository.updateRepoById(id, repo);
    }

}
