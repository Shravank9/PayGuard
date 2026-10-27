package com.Shravan.PayGuard.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Shravan.PayGuard.modal.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
