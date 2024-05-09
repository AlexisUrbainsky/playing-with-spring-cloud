package com.alexis.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/price/**")
                .filters(f -> f.setResponseHeader("Hi", "I am the Gateway"))
                        .uri("http://localhost:8002"))
                .route(r -> r.path("/inventory/**")
                        .uri("http://localhost:8003")).build();
    }


}
