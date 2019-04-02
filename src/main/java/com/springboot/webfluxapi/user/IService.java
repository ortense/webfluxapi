package com.springboot.webfluxapi.user;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IService {
    Flux<User> findAll();
    Mono<User> findById(String id);
    Mono<User> save(User user);
    Mono<User> update(String id, User user);
    Mono<User> delete(String id);
}