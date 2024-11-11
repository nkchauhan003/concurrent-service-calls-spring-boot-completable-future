package com.cb.client.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class OrderClient {

    @Value("${order.service.url}")
    private String orderServiceUrl;

    private final RestTemplate restTemplate;

    public OrderClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    public CompletableFuture<Order> getOrder(String orderId) {
        log.info("Fetching order from order service for order id: {}", orderId);
        var url = orderServiceUrl + "/orders?orderId=" + orderId;
        var order = restTemplate.getForObject(url, Order.class);
        log.info("Order fetched successfully for order id: {}", orderId);
        return CompletableFuture.completedFuture(order);
    }
}
