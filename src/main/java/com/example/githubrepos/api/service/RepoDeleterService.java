package com.example.githubrepos.api.service;

import com.example.githubrepos.database.repository.RepoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class RepoDeleterService {
    private final RepoRepository repoRepository;
    private final RepoRetrieverService repoRetrieverService;

    public void deleteRepo(Long id) {
        repoRetrieverService.repoExistsById(id);
        repoRepository.deleteRepoById(id);
    }
}
