package com.springboot.webfluxapi.user;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.ServerResponse.created;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class UserHandler {
    @Autowired
    IService service;

    private Mono<ServerResponse> returnUserIfExist(Mono<User> stream) {
        return stream
            .flatMap(user -> ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromObject(user)))
            .switchIfEmpty(notFound().build());
    }

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
        return this.returnUserIfExist(service.findById(request.pathVariable("id")));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        return this.returnUserIfExist(
            request
                .body(BodyExtractors.toMono(User.class))
                .flatMap(user -> service.update(request.pathVariable("id"), user)));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        return this.returnUserIfExist(service.delete(request.pathVariable("id")));
    }
}
