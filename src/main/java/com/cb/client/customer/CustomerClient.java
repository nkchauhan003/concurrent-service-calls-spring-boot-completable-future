package com.cb.client.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class CustomerClient {

    @Value("${customer.service.url}")
    private String customerServiceUrl;

    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    public CompletableFuture<Customer> getCustomer(String customerId) {
        log.info("Fetching customer from customer service for customer id: {}", customerId);
        var url = customerServiceUrl + "/customers?customerId=" + customerId;
        var customer = restTemplate.getForObject(url, Customer.class);
        log.info("Customer fetched successfully for customer id: {}", customerId);
        return CompletableFuture.completedFuture(customer);
    }
}
