package com.springboot.webfluxapi.user;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> createUserRouter(UserHandler handler) {
        return RouterFunctions
            .route(POST("/users").and(accept(MediaType.APPLICATION_JSON)), handler::create)
            .andRoute(GET("/users"), handler::retrieve)
            .andRoute(GET("/users/{id}"), handler::findById)
            .andRoute(PUT("/users/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::update)
            .andRoute(DELETE("/users/{id}"), handler::delete);
    }
}
