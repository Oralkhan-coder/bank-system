package kz.aitu.banksystem.credit.controller;

import jakarta.persistence.EntityNotFoundException;
import kz.aitu.banksystem.credit.model.CreditType;
import kz.aitu.banksystem.credit.model.dto.CreditViewResponseDto;
import kz.aitu.banksystem.credit.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credits")
public class CreditController {

    private final CreditService creditService;

    @PostMapping
    public ResponseEntity<CreditViewResponseDto> createCredit(
            @RequestParam CreditType type,
            @RequestParam Double amount,
            @RequestParam(required = false) Double interestRate,
            @RequestParam Integer termMonths,
            @RequestParam Long userId) {
        CreditViewResponseDto credit = creditService.createCredit(type, amount, interestRate, termMonths, userId);
        return ResponseEntity.ok(credit);
    }

    @PostMapping("/{id}/payment")
    public ResponseEntity<CreditViewResponseDto> makePayment(
            @PathVariable Long id,
            @RequestParam Double paymentAmount) {
        try {
            CreditViewResponseDto credit = creditService.makePayment(id, paymentAmount);
            return ResponseEntity.ok(credit);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditViewResponseDto> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(creditService.findById(id));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public List<CreditViewResponseDto> findAllByUserId(@PathVariable Long userId) {
        return creditService.findAllByUserId(userId);
    }

    @GetMapping
    public List<CreditViewResponseDto> findAll() {
        return creditService.findAll();
    }

    @PostMapping("/check-overdue")
    public ResponseEntity<?> checkOverdueCredits() {
        creditService.checkOverdueCredits();
        return ResponseEntity.ok().build();
    }
}

