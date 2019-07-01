package com.demo.reactiveserver.config.filter;

import com.demo.reactiveserver.config.DemoProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class HeaderContextFilter implements WebFilter {

    private final List<String> headers;

    public HeaderContextFilter(DemoProperties demoProperties) {
        headers = demoProperties.getHeaders();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return chain.filter(exchange)
                .subscriberContext(context -> {
                    Map<String, String> headerMap = new HashMap<>();
                    headers.forEach(header -> headerMap.put(header, exchange.getRequest().getHeaders().getFirst(header)));
                    context = context.put("headers", headerMap);
                    return context;
                });
    }
}
