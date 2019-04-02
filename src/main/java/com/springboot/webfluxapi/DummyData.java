package com.springboot.webfluxapi;

import java.util.UUID;

import com.springboot.webfluxapi.user.IRepository;
import com.springboot.webfluxapi.user.User;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

@Component
public class DummyData implements CommandLineRunner {
    private final IRepository userRepository;

    DummyData(IRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll()
            .thenMany(
                Flux.just("Fulano", "Beltrano", "Cicrano")
                .map(name -> new User(UUID.randomUUID().toString(), name))
                .flatMap(userRepository::save))
            .subscribe(System.out::println);
    }
}