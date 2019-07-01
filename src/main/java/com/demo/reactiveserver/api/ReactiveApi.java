package com.demo.reactiveserver.api;

import com.demo.reactiveserver.model.UserDetails;
import com.demo.reactiveserver.service.ReactiveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ReactiveApi {

    private final ReactiveService reactiveService;

    @GetMapping("/users/{userId}")
    public Mono<UserDetails> getUserDetails(@PathVariable String userId) {
        return reactiveService.getUserDetails(userId);
    }
}
