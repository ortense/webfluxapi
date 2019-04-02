package com.springboot.webfluxapi.user;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.ServerResponse.created;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class Handler {
    @Autowired
    IService service;

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.body(BodyExtractors.toMono(User.class))
            .flatMap(user -> service.save(user))
            .flatMap(user -> created(URI.create("/users/" + user.getId()))
                .contentType(MediaType.APPLICATION_JSON)    
                .body(fromObject(user)));
    }

    public Mono<ServerResponse> retrieve(ServerRequest request) {
        return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(service.findAll(), User.class);
            
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(service.findById(request.pathVariable("id")), User.class);
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        return request.body(BodyExtractors.toMono(User.class))
            .flatMap(user -> service.update(request.pathVariable("id"), user))
            .flatMap(user -> ok().body(fromObject(user)));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(service.delete(request.pathVariable("id")), User.class);
    }
}
