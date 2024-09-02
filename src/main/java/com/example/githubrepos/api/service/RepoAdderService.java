package com.example.githubrepos.api.service;

import com.example.githubrepos.database.entity.Repo;
import com.example.githubrepos.database.repository.RepoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class RepoAdderService {
    private final RepoRepository repoRepository;

    public Repo addRepo(Repo repo) {
        return repoRepository.save(repo);
    }
}
