package com.example.githubrepos.database.repository;

import com.example.githubrepos.database.entity.Repo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface RepoRepository extends Repository<Repo, Long> {

    @Query("SELECT r FROM Repo r")
    List<Repo> getAllRepos(Pageable pageable);

    @Query("SELECT r FROM Repo r WHERE r.owner = :username")
    List<Repo> getReposByUsername(Pageable pageable, String username);

    @Query("SELECT r FROM Repo r WHERE r.id = :id")
    Repo getRepoById(Long id);

    @Modifying
    @Query("DELETE FROM Repo r WHERE r.id = :id")
    void deleteRepoById(Long id);

    @Modifying
    @Query("UPDATE Repo r SET r.owner = :#{#repo.owner}, r.name = :#{#repo.name}  WHERE r.id = :id")
    void updateRepoById(Long id, Repo repo);

    Repo save(Repo repo);

    boolean existsByOwner(String username);

    boolean existsRepoById(Long id);
}
