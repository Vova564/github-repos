package com.example.githubrepos;

import com.example.githubrepos.client.GitHubApiClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableFeignClients
public class GithubReposApplication {

	private final GitHubApiClient gitHubApiClient;

    public GithubReposApplication(GitHubApiClient gitHubApiClient) {
        this.gitHubApiClient = gitHubApiClient;
    }

    public static void main(String[] args) {
		SpringApplication.run(GithubReposApplication.class, args);
	}

	@EventListener(ApplicationStartedEvent.class)
	public void applicationStarted() {
//		gitHubApiClient.getRepositories("kalqa").forEach(repo -> System.out.println(repo.name() + " " + repo.owner().login()));
		gitHubApiClient.getBranches("kalqa", "songify").forEach(branch -> System.out.println(branch.name() + " - " + branch.commit().sha()));
	}
}
