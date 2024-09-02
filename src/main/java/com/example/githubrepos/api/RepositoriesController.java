package com.example.githubrepos.api;

import com.example.githubrepos.api.dto.request.CreateRepoRequestDTO;
import com.example.githubrepos.api.dto.request.UpdateRepoRequestDTO;
import com.example.githubrepos.api.dto.response.*;
import com.example.githubrepos.api.error.exception.BadContentTypeException;
import com.example.githubrepos.api.service.*;
import com.example.githubrepos.database.entity.Repo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.githubrepos.api.mapper.RepoMapper.*;

@RestController
@AllArgsConstructor
@RequestMapping("/repos")
public class RepositoriesController {

    private final RepositoriesService repositoriesService;
    private final RepoRetrieverService repoRetrieverService;
    private final RepoAdderService repoAdderService;
    private final RepoDeleterService repoDeleterService;
    private final RepoUpdaterService repoUpdaterService;

    @GetMapping("/{username}")
    ResponseEntity<List<GitHubRepositoriesResponseDTO>> getRepos(@RequestHeader(HttpHeaders.ACCEPT) String accept, @PathVariable String username) {
        if (!accept.equals("application/json")) {
            if (!accept.equals("*/*")) {
                throw new BadContentTypeException("Provided bad content type. Expect application/json but got " + accept);
            }
        }

        List<GitHubRepositoriesResponseDTO> responseBody = repositoriesService.getUserRepos(username);
        return ResponseEntity.status(200).body(responseBody);
    }

    @GetMapping("/database")
    ResponseEntity<GetAllReposResponseDTO> getAllReposFromDb(@PageableDefault Pageable pageable) {
        List<Repo> allRepos = repoRetrieverService.getAllRepos(pageable);

        GetAllReposResponseDTO responseBody = mapFromRepoToGetAllReposResponseDTO(allRepos);
        return ResponseEntity.status(200).body(responseBody);
    }

    @GetMapping("/database/username/{username}")
    ResponseEntity<GetUserReposByUsernameResponseDTO> getReposFromDbByUsername(@PageableDefault Pageable pageable, @PathVariable String username) {
        List<Repo> allUserRepos = repoRetrieverService.getReposByUsername(pageable, username);

        GetUserReposByUsernameResponseDTO responseBody = mapFromRepoToGetUserReposByUsernameResponseDTO(allUserRepos);
        return ResponseEntity.status(200).body(responseBody);
    }

    @GetMapping("/database/id/{id}")
    ResponseEntity<GetRepoByIdResponseDTO> getRepoFromDbById(@PathVariable Long id) {
        Repo repo = repoRetrieverService.getRepoById(id);

        GetRepoByIdResponseDTO responseBody = mapFromRepoToGetRepoByIdResponseDTO(repo);
        return ResponseEntity.status(200).body(responseBody);
    }

    @PostMapping("/database")
    ResponseEntity<CreateRepoResponseDTO> addRepoToDb(@Valid @RequestBody CreateRepoRequestDTO requestBody) {
        Repo repo = mapFromCreateRepoRequestDTOToRepo(requestBody);
        Repo savedRepo = repoAdderService.addRepo(repo);

        CreateRepoResponseDTO responseBody = mapFromRepoToCreateRepoResponseDTO(savedRepo);
        return ResponseEntity.status(200).body(responseBody);
    }

    @DeleteMapping("/database/{id}")
    ResponseEntity<DeleteRepoResponseDTO> deleteRepoFromDb(@PathVariable Long id) {
        repoDeleterService.deleteRepo(id);
        DeleteRepoResponseDTO responseBody = mapDeleteRepoResponseDTO(id);
        return ResponseEntity.status(200).body(responseBody);
    }

    @PutMapping("/database/{id}")
    ResponseEntity<UpdateRepoResponseDTO> updateRepoById(@Valid @RequestBody UpdateRepoRequestDTO requestBody, @PathVariable Long id) {
        Repo repo = mapFromUpdateRepoRequestDTOToRepo(requestBody);
        repoUpdaterService.updateRepoById(id, repo);

        UpdateRepoResponseDTO responseBody = mapUpdateRepoResponseDTO(id);
        return ResponseEntity.status(200).body(responseBody);
    }

}

