package com.demo.reactiveserver.config.exchange;

import com.demo.reactiveserver.config.DemoProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
public class HeaderExchange implements ExchangeFilterFunction {

    private List<String> headers;

    public HeaderExchange(DemoProperties demoProperties) {
        this.headers = demoProperties.getHeaders();
    }

    @Override
    public Mono<ClientResponse> filter(ClientRequest clientRequest, ExchangeFunction exchangeFunction) {
        return Mono.subscriberContext()
                .flatMap(context -> {
                    Map<String, String> headerMap = context.get("headers");
                    ClientRequest newRequest = ClientRequest
                            .from(clientRequest)
                            .headers(httpHeaders -> headers.forEach(header -> httpHeaders.add(header, headerMap.get(header))))
                            .build();
                    return exchangeFunction.exchange(newRequest);
                });
    }
}
