package com.cb.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentInfo {
    private String paymentId;
    private String orderId;
    private double amount;
    private String paymentMethod;
    private String paymentStatus;
}
