package com.fpt.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/get")
                        .uri("http://httpbin.org:80"))
                .route(p -> p.path("/greeting-service/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://greeting-service"))
                .build();
    }

}
