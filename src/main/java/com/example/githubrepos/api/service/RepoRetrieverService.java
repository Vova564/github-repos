package com.example.githubrepos.api.service;

import com.example.githubrepos.api.error.exception.RecordsNotFoundException;
import com.example.githubrepos.database.entity.Repo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import com.example.githubrepos.database.repository.RepoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RepoRetrieverService {
    private final RepoRepository repoRepository;

    public List<Repo> getAllRepos(Pageable pageable) {
        return repoRepository.getAllRepos(pageable);
    }

    public List<Repo> getReposByUsername(Pageable pageable, String username) {
        repoExistsByUsername(username);
        return repoRepository.getReposByUsername(pageable, username);
    }

    public Repo getRepoById(Long id) {
        repoExistsById(id);
        return repoRepository.getRepoById(id);
    }

    public void repoExistsByUsername(String username) {
        if (!repoRepository.existsByOwner(username)) {
            throw new RecordsNotFoundException("Records with username " + username + " not found");
        }
    }

    public void repoExistsById(Long id) {
        if (!repoRepository.existsRepoById(id)) {
            throw new RecordsNotFoundException("Record with id " + id + " not found");
        }
    }
}
