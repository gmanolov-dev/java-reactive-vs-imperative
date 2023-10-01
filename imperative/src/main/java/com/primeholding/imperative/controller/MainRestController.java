package com.primeholding.imperative.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainRestController {

    RestTemplate restTemplate = new RestTemplate();


    @GetMapping("/delayed-response-from-remote")
    public String delayedResponseFromServer() {
        return restTemplate.getForObject("http://localhost:7070", String.class);
    }

    @GetMapping("/delayed-response-from-local")
    public String delayedResponseFromLocal() throws InterruptedException {
        Thread.sleep(3000);
        return "Hello from imperative controller";
    }
}
