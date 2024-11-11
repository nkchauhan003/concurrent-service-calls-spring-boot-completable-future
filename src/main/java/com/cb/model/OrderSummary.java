package com.cb.model;

import com.cb.client.customer.Customer;
import com.cb.client.inventory.Inventory;
import com.cb.client.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderSummary {
    private Order order;
    private Customer customer;
    private Inventory inventory;
    private PaymentInfo paymentInfo;
}