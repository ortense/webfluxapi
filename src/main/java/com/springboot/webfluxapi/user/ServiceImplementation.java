package com.springboot.webfluxapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServiceImplementation implements IService {

    @Autowired
    IRepository repository;

    @Override
    public Flux<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<User> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<User> save(User user) {
        return repository.save(user);
    }

    @Override 
    public Mono<User> update(String id, User user) {
        return repository
            .findById(id)
            .map(u -> {
                u.setName(user.getName());
                return u;
            })
            .flatMap(u -> repository.save(u));
    }

    @Override
    public Mono<User> delete(String id) {
        return repository
            .findById(id)
            .flatMap(user -> repository.deleteById(id).thenReturn(user));
    }
}
