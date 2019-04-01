package com.springboot.webfluxapi.repository;

import com.springboot.webfluxapi.document.User;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IUserRepository extends ReactiveMongoRepository<User, String> {
}
