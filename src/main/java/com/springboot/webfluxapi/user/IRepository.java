package com.springboot.webfluxapi.user;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IRepository extends ReactiveMongoRepository<User, String> {
}