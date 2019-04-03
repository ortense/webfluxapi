package com.springboot.webfluxapi.githubrepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServiceImplementation implements IService {
    
    private final WebClient client;

    @Autowired
    public ServiceImplementation() {
        this.client = WebClient.create("https://api.github.com/");
    }

    private Mono<ClientResponse> apiGet(String uri) {
        return client.get().uri(uri).exchange();
    }

    @Override
    public Flux<GithubRepo> findByOwnerName(String ownerName) {
        return this.apiGet("/users/" + ownerName + "/repos")
            .flatMapMany(response -> response.bodyToFlux(GithubRepo.class));
    }

    @Override
    public Mono<GithubRepo> findByRepoName(String ownerName, String repoName) {
        return this.apiGet("/users/" + ownerName + "/repos/" + repoName)
            .flatMap(response -> response.bodyToMono(GithubRepo.class));
    }

}