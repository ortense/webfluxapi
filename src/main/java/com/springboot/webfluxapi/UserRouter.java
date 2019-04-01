package com.springboot.webfluxapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> route(UserHandler handler) {
        return RouterFunctions
            .route(POST("/users").and(accept(MediaType.APPLICATION_JSON)), handler::create)
            .andRoute(GET("/users").and(accept(MediaType.APPLICATION_JSON)), handler::retrieve)
            .andRoute(GET("/users/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::findById);
    }
}
