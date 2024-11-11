package com.cb.service;

import com.cb.client.customer.Customer;
import com.cb.client.customer.CustomerClient;
import com.cb.client.inventory.Inventory;
import com.cb.client.inventory.InventoryClient;
import com.cb.client.order.Order;
import com.cb.client.order.OrderClient;
import com.cb.model.OrderSummary;
import com.cb.model.PaymentInfo;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class OrderAggregationService {

    private final OrderClient orderClient;
    private final CustomerClient customerClient;
    private final InventoryClient inventoryClient;
    private final PaymentService paymentService;

    public OrderAggregationService(OrderClient orderClient, CustomerClient customerClient, InventoryClient inventoryClient, PaymentService paymentService) {
        this.orderClient = orderClient;
        this.customerClient = customerClient;
        this.inventoryClient = inventoryClient;
        this.paymentService = paymentService;
    }

    public CompletableFuture<OrderSummary> aggregateOrderData(String orderId, String customerId, String productId) throws InterruptedException {

        // Trigger asynchronous calls
        var orderFuture = orderClient.getOrder(orderId);
        var customerFuture = customerClient.getCustomer(customerId);
        var inventoryFuture = inventoryClient.getInventory(productId);
        var paymentFuture = paymentService.getPaymentInfo(orderId);

        // Wait for all the futures to complete and get the results
        return CompletableFuture.allOf(orderFuture, customerFuture, inventoryFuture, paymentFuture)
                .thenApply(v -> {
                    Order order = orderFuture.join();
                    Customer customer = customerFuture.join();
                    Inventory inventory = inventoryFuture.join();
                    PaymentInfo paymentInfo = paymentFuture.join();
                    return new OrderSummary(order, customer, inventory, paymentInfo);
                });
    }
}
