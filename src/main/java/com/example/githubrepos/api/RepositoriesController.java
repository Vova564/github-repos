package com.example.githubrepos.api;

import com.example.githubrepos.api.dto.RepositoriesResponseDTO;
import com.example.githubrepos.api.error.exception.BadContentTypeException;
import com.example.githubrepos.api.service.RepositoriesService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repos")
public class RepositoriesController {

    private final RepositoriesService repositoriesService;

    public RepositoriesController(RepositoriesService repositoriesService) {
        this.repositoriesService = repositoriesService;
    }

    @GetMapping("/{username}")
    ResponseEntity<List<RepositoriesResponseDTO>> getRepos(@RequestHeader(HttpHeaders.ACCEPT) String accept, @PathVariable String username) {
        if (!accept.equals("application/json")) {
            if (!accept.equals("*/*")) {
                throw new BadContentTypeException("Provided bad content type. Expect application/json but got " + accept);
            }
        }

        List<RepositoriesResponseDTO> responseBody = repositoriesService.getUserRepos(username);
        return ResponseEntity.status(200).body(responseBody);
    }
}

