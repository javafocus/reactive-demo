package com.demo.reactiveserver.config;

import com.demo.reactiveserver.model.UserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

@Slf4j
@Component
@RequiredArgsConstructor
public class BackendServer {

    private HttpServer backendServer;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void startBackend() throws IOException {
        backendServer = HttpServer.create(new InetSocketAddress(8181), 0);
        backendServer.createContext("/backend/users", new UserHandler(objectMapper));
        backendServer.setExecutor(null);
        backendServer.start();
        log.info("backend server started");
    }

    @PreDestroy
    public void stopBackend() {
        backendServer.stop(8181);
        log.info("backend server stopped");
    }

    static class UserHandler implements HttpHandler {

        private ObjectMapper objectMapper;

        UserHandler(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            String[] paths = httpExchange.getRequestURI().getPath().split("/");
            Headers requestHeaders = httpExchange.getRequestHeaders();
            log.info("clientId "+requestHeaders.getFirst("client_id"));
            log.info("requestId "+requestHeaders.getFirst("request_id"));
            String response = objectMapper.writeValueAsString(getUserDetails(paths[paths.length - 1]));
            httpExchange.getResponseHeaders().add("content-type", MediaType.APPLICATION_JSON_VALUE);
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.flush();
            os.close();
        }

        private UserDetails getUserDetails(String userId) {
            return new UserDetails(userId, "test", "21", "test");
        }
    }
}
