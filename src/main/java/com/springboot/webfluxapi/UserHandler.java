package com.springboot.webfluxapi;

import com.springboot.webfluxapi.document.User;
import com.springboot.webfluxapi.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.ServerResponse.created;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import java.net.URI;

import reactor.core.publisher.Mono;

@Component
public class UserHandler {
    @Autowired
    IUserService service;

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.body(BodyExtractors.toMono(User.class))
            .flatMap(user -> service.save(user))
            .flatMap(user -> created(URI.create("/users/" + user.getId()))
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
}
