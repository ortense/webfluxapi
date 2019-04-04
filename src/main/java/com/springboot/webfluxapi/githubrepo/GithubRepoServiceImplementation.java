package com.springboot.webfluxapi.githubrepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GithubRepoServiceImplementation implements IService {
    
    private final WebClient client;

    @Autowired
    public GithubRepoServiceImplementation() {
        this.client = WebClient.create("https://api.github.com/");
    }

    private Mono<ClientResponse> apiGet(String uri) {
        return client.get().uri(uri).exchange();
    }

    @Override
    public Flux<GithubRepo> findByOwnerName(String ownerName) {
        Mono<ClientResponse> apiGet = this.apiGet("/users/" + ownerName + "/repos");
        return apiGet
            .flatMapMany(response -> response.statusCode().value() == 200
                ? response.bodyToFlux(GithubRepo.class)
                : Flux.just());
    }

    @Override
    public Mono<GithubRepo> findByRepoName(String ownerName, String repoName) {
        return this.apiGet("/repos/" + ownerName + "/" + repoName)
            .flatMap(response -> response.bodyToMono(GithubRepo.class));
    }

}