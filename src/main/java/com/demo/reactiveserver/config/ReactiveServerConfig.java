package com.demo.reactiveserver.config;

import com.demo.reactiveserver.config.exchange.HeaderExchange;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(DemoProperties.class)
public class ReactiveServerConfig {

    @Bean
    public WebClient webClient(DemoProperties demoProperties, HeaderExchange headerExchange) {
        return WebClient
                .builder()
                .filter(headerExchange)
                .baseUrl(demoProperties.getBackendUri())
                .build();
    }
}
