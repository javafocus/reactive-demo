package com.demo.reactiveserver.service;

import com.demo.reactiveserver.model.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReactiveService {

    private final WebClient webClient;

    public Mono<UserDetails> getUserDetails(String userId) {
        return webClient
                .get()
                .uri("users/{userId}", userId)
                .retrieve()
                .bodyToMono(UserDetails.class);
    }
}
