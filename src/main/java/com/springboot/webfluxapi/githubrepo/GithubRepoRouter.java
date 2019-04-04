package com.springboot.webfluxapi.githubrepo;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class GithubRepoRouter {

    @Bean
    public RouterFunction<ServerResponse> createGithubRepoRouter(GithubRepoHandler handler) {
        return RouterFunctions
            .route(GET("/respos/{owner}"), handler::findUserRepos)
            .andRoute(GET("/respos/{owner}/{repo}"), handler::getRepo);
    }
}