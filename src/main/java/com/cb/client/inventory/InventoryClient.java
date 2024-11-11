package com.cb.client.inventory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class InventoryClient {

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    private final RestTemplate restTemplate;

    public InventoryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    public CompletableFuture<Inventory> getInventory(String productId) {
        log.info("Fetching inventory from inventory service for product id: {}", productId);
        var url = inventoryServiceUrl + "/inventory?productId=" + productId;
        var inventory = restTemplate.getForObject(url, Inventory.class);
        log.info("Inventory fetched successfully for product id: {}", productId);
        return CompletableFuture.completedFuture(inventory);
    }
}
