package com.demo.reactiveserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "demo")
public class DemoProperties {
    private String backendUri;

    private List<String> headers;
}
