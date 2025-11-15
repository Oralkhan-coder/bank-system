package kz.aitu.banksystem.payment.controller;

import kz.aitu.banksystem.payment.model.dto.request.PaymentRequestDto;
import kz.aitu.banksystem.payment.service.BankPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final BankPaymentService bankPaymentService;

    @PostMapping("/google-pay")
    public ResponseEntity<Void> payWithGoogle(@RequestBody PaymentRequestDto request) {
        bankPaymentService.processPayment(request.getAmount(), request.getCurrency());
        return ResponseEntity.ok().build();
    }
}