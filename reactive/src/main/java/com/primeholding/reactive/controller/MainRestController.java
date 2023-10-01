package com.primeholding.reactive.controller;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainRestController {

    WebClient webClient;

    @PostConstruct
    public void init() {
        ConnectionProvider connectionProvider = ConnectionProvider.builder("myConnectionPool")
                .maxConnections(10_005)
                .pendingAcquireMaxCount(10_005)
                .build();
        ReactorClientHttpConnector clientHttpConnector = new ReactorClientHttpConnector(HttpClient.create(connectionProvider));
        webClient = WebClient.builder()
                .clientConnector(clientHttpConnector)
                .build();
    }

    @GetMapping("/delayed-response-from-remote")
    public Mono<String> delayedResponseFromRemote() {
        return webClient.get().uri("http://localhost:7070").retrieve().bodyToMono(String.class);
    }


    @GetMapping("/delayed-response-from-local")
    public Mono<String> delayedResponseFromLocal() {
        return Mono.just("Hello from reactive controller").delayElement(Duration.ofSeconds(3));
    }
}
