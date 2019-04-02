package com.springboot.webfluxapi.user;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class Router {

    @Bean
    public RouterFunction<ServerResponse> createUserRouter(Handler handler) {
        return RouterFunctions
            .route(POST("/users").and(accept(MediaType.APPLICATION_JSON)), handler::create)
            .andRoute(GET("/users").and(accept(MediaType.APPLICATION_JSON)), handler::retrieve)
            .andRoute(GET("/users/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::findById);
    }
}
