package kz.aitu.banksystem.payment.controller;

import kz.aitu.banksystem.payment.adapter.GooglePayAdapter;
import kz.aitu.banksystem.payment.model.dto.request.PaymentRequestDto;
import kz.aitu.banksystem.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService service;
    private final GooglePayAdapter googlePayService;

    @PostMapping("/pay")
    public ResponseEntity<Void> pay(@RequestBody PaymentRequestDto request) {
        service.pay(request.getAmount(), request.getAccountNumber());
        return ResponseEntity.ok().build();
    }


    @PostMapping("/google-pay")
    public ResponseEntity<Void> payWithGoogle(@RequestBody PaymentRequestDto request) {
        googlePayService.payByGoogle(request.getAmount(), request.getAccountNumber());
        return ResponseEntity.ok().build();
    }
}