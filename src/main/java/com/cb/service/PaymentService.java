package com.cb.service;

import com.cb.model.PaymentInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class PaymentService {

    public CompletableFuture<PaymentInfo> getPaymentInfo(String paymentId) throws InterruptedException {
        // 500 ms delay to simulate calculation time
        TimeUnit.MILLISECONDS.sleep(500);
        // Using builder pattern to create PaymentInfo object
        var paymentInfo = PaymentInfo.builder()
                .paymentId(paymentId)
                .orderId("order123")
                .amount(100.0)
                .paymentMethod("Credit Card")
                .paymentStatus("Completed")
                .build();
        return CompletableFuture.completedFuture(paymentInfo);
    }
}