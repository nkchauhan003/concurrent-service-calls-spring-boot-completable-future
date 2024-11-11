package com.cb.client.order;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
    private String orderId;
    private String product;
    private int quantity;
    private double price;
}
