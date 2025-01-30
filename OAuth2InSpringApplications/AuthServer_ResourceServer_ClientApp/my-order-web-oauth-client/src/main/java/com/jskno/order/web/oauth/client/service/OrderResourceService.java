package com.jskno.order.web.oauth.client.service;

import com.jskno.order.web.oauth.client.model.Order;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class OrderResourceService {

    private final WebClient webClient;
    private final RestTemplate restTemplate;

    public OrderResourceService(WebClient.Builder webClientBuilder, RestTemplate restTemplate) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8091").build();
        this.restTemplate = restTemplate;
    }

    public Mono<List<Order>> getAllOrders(String accessToken) {
        return this.webClient.get()
                .uri("/orders")
                .headers(headers -> headers.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Order>>() {});
    }

    public List<Order> getRestTemplateOrders(String accessToken) {

        String url = "http://localhost:8091/orders";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer: " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Order>> response = this.restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Order>>() {});

        return response.getBody();
    }
}
