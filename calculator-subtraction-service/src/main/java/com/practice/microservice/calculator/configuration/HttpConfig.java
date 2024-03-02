package com.practice.microservice.calculator.configuration;

import com.practice.microservice.calculator.service.remote.RoundClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpConfig {

    private static final String EXTERNAL_ROUND_SERVICE = "http://round-service:8095";

    @Bean
    public RoundClient roundClient() {
        WebClient webClient = WebClient.builder()
            .baseUrl(EXTERNAL_ROUND_SERVICE)
            .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
            .builder(WebClientAdapter.forClient(webClient))
            .build();
        return factory.createClient(RoundClient.class);
    }
}
