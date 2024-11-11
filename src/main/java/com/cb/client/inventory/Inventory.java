package com.cb.client.inventory;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Inventory {
    private String productId;
    private String status;
    private int availableQuantity;
}
