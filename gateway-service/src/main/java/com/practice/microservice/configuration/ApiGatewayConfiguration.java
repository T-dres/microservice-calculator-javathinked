package com.practice.microservice.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator configureGatewayRoute(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
            .route("addition_route", route -> route
                .path("/api/addition/**")
                .uri("http://addition-service:8091"))
            .route("subtraction_route", route -> route
                .path("/api/subtraction/**")
                .uri("http://subtraction-service:8092"))
            .route("multiplication_route", route -> route
                .path("/api/multiplication/**")
                .uri("http://multiplication-service:8093"))
            .route("division_route", route -> route
                .path("/api/division/**")
                .uri("http://division-service:8094"))
            .build();
    }
}
