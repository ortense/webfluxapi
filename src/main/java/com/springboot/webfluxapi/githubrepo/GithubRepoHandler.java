package com.springboot.webfluxapi.githubrepo;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class GithubRepoHandler {
    @Autowired
    IService service;

    public Mono<ServerResponse> findUserRepos(ServerRequest request) {
        return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(service.findByOwnerName(request.pathVariable("owner")), GithubRepo.class);
    }

    public Mono<ServerResponse> getRepo(ServerRequest request) {
        return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(service
                .findByRepoName(request.pathVariable("owner"), request.pathVariable("repo")),
                    GithubRepo.class);
    }

}