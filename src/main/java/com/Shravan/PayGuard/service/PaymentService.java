package com.Shravan.PayGuard.service;

import java.math.BigDecimal;

import com.Shravan.PayGuard.modal.Payment;

public interface PaymentService {

    Payment createPayment(BigDecimal amount, String currency, String idempotencyKey);

}