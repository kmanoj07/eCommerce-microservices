package com.kumarmanoj.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

//    @Bean
//    public WebClient webClient(){
//        // create a WebClient object
//        return WebClient.builder()
//                .build();
//    }

    // Handling Client Side Load Balancing when make API Call with Web Client
    // It will handle the multiple instance calling issue
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder(){
        // create a WebClient object
        return WebClient.builder();
    }
}
