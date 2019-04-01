package com.springboot.webfluxapi.services;

import com.springboot.webfluxapi.document.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {
    Flux<User> findAll();
    Mono<User> findById(String id);
    Mono<User> save(User user);
}
