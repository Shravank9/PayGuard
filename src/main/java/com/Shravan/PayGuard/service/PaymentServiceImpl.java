
package com.Shravan.PayGuard.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Shravan.PayGuard.modal.Payment;
import com.Shravan.PayGuard.modal.IdempotencyKey;
import com.Shravan.PayGuard.repo.PaymentRepository;
import com.Shravan.PayGuard.repo.IdempotencyKeyRepository;
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final IdempotencyKeyRepository idempotencyKeyRepository;

    public PaymentServiceImpl(
            PaymentRepository paymentRepository,
            IdempotencyKeyRepository idempotencyKeyRepository) {

        this.paymentRepository = paymentRepository;
        this.idempotencyKeyRepository = idempotencyKeyRepository;
    }

    @Override
    @Transactional
    public Payment createPayment(
            BigDecimal amount,
            String currency,
            String idempotencyKey) {

        // 1. Check whether the key already exists
        var existingKey = idempotencyKeyRepository
                .findByKeyValue(idempotencyKey);

        if (existingKey.isPresent()) {
            return existingKey.get().getPayment();
        }

        // 2. Create a new payment
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setCurrency(currency);

        // 3. Save the payment
        Payment savedPayment = paymentRepository.save(payment);

        // 4. Create the idempotency key
        IdempotencyKey key = new IdempotencyKey();
        key.setKeyValue(idempotencyKey);
        key.setPayment(savedPayment);

        // 5. Save the key
        idempotencyKeyRepository.save(key);

        // 6. Return the saved payment
        return savedPayment;
    }
}