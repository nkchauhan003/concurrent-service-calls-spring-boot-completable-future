package com.cb.controller;

import com.cb.model.OrderSummary;
import com.cb.service.OrderAggregationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/orders")
@Slf4j
public class OrderController {

    private final OrderAggregationService orderAggregationService;

    @Autowired
    public OrderController(OrderAggregationService orderAggregationService) {
        this.orderAggregationService = orderAggregationService;
    }

    @GetMapping("/summary/{orderId}/{customerId}/{productId}")
    public CompletableFuture<OrderSummary> getOrderSummary(@PathVariable String orderId,
                                                           @PathVariable String customerId,
                                                           @PathVariable String productId) throws InterruptedException {
        var startTime = System.currentTimeMillis();
        log.info("Fetching order summary for order id: {}, customer id: {}, product id: {}", orderId, customerId, productId);
        return orderAggregationService.aggregateOrderData(orderId, customerId, productId)
                .thenApply(orderSummary -> {
                    log.info("Order summary fetched successfully for order id: {}, customer id: {}, product id: {} in {} ms", orderId, customerId, productId, System.currentTimeMillis() - startTime);
                    return orderSummary;
                });
    }
}