
package com.Shravan.PayGuard.contoller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Shravan.PayGuard.modal.Payment;
import com.Shravan.PayGuard.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	private final PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@PostMapping
	public ResponseEntity<Payment> createPayment(@RequestHeader("Idempotency-Key") String idempotencyKey,
			@RequestParam BigDecimal amount, @RequestParam(defaultValue = "INR") String currency) {

		Payment payment = paymentService.createPayment(amount, currency, idempotencyKey);

		return ResponseEntity.ok(payment);
	}
}