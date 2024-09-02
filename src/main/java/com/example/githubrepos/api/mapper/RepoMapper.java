package com.example.githubrepos.api.mapper;

import com.example.githubrepos.api.dto.request.CreateRepoRequestDTO;
import com.example.githubrepos.api.dto.request.UpdateRepoRequestDTO;
import com.example.githubrepos.api.dto.response.*;
import com.example.githubrepos.database.entity.Repo;

import java.util.List;

public class RepoMapper {

    public static Repo mapFromCreateRepoRequestDTOToRepo(CreateRepoRequestDTO requestDTO) {
        return new Repo(requestDTO.owner(), requestDTO.name());
    }

    public static Repo mapFromUpdateRepoRequestDTOToRepo(UpdateRepoRequestDTO requestDTO) {
        return new Repo(requestDTO.owner(), requestDTO.name());
    }

    public static RepoDTO mapFromRepoToRepoDTO(Repo repo) {
        return new RepoDTO(repo.getId(), repo.getOwner(), repo.getName());
    }

    public static GetAllReposResponseDTO mapFromRepoToGetAllReposResponseDTO(List<Repo> repos) {
        List<RepoDTO> repoDTOs = repos.stream()
                .map(RepoMapper::mapFromRepoToRepoDTO)
                .toList();
        return new GetAllReposResponseDTO(repoDTOs);
    }

    public static GetUserReposByUsernameResponseDTO mapFromRepoToGetUserReposByUsernameResponseDTO(List<Repo> repos) {
        List<RepoDTO> repoDTOs = repos.stream()
                .map(RepoMapper::mapFromRepoToRepoDTO)
                .toList();
        return new GetUserReposByUsernameResponseDTO(repoDTOs);
    }

    public static GetRepoByIdResponseDTO mapFromRepoToGetRepoByIdResponseDTO(Repo repo) {
        RepoDTO repoDTO = mapFromRepoToRepoDTO(repo);
        return new GetRepoByIdResponseDTO(repoDTO);
    }

    public static CreateRepoResponseDTO mapFromRepoToCreateRepoResponseDTO(Repo repo) {
        RepoDTO repoDTO = mapFromRepoToRepoDTO(repo);
        return new CreateRepoResponseDTO(repoDTO);
    }

    public static DeleteRepoResponseDTO mapDeleteRepoResponseDTO(Long id) {
        return new DeleteRepoResponseDTO(id, "Deleted repo with id " + id);
    }

    public static UpdateRepoResponseDTO mapUpdateRepoResponseDTO(Long id) {
        return new UpdateRepoResponseDTO(id, "Updated repo with id " + id);
    }

}
