package com.springboot.webfluxapi.githubrepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IService {
    Flux<GithubRepo> findByOwnerName(String ownerName);
    Mono<GithubRepo> findByRepoName(String ownerName, String repoName);
}