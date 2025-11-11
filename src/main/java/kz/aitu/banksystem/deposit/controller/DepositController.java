package kz.aitu.banksystem.deposit.controller;

import kz.aitu.banksystem.deposit.model.DepositType;
import kz.aitu.banksystem.deposit.model.entity.DepositEntity;
import kz.aitu.banksystem.deposit.service.DepositService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deposits")
public class DepositController {

    private final DepositService depositService;

    @PostMapping
    public ResponseEntity<DepositEntity> createDeposit(
            @RequestParam DepositType type,
            @RequestParam Double initialAmount,
            @RequestParam Double interestRate,
            @RequestParam Long userId,
            @RequestParam Integer durationMonths)
    {
        return ResponseEntity.ok(depositService.createDeposit(type, initialAmount, interestRate, userId, durationMonths));
    }

    @GetMapping("/{id}/interest")
    public ResponseEntity<Double> calculateInterest(@PathVariable Long id) {
        return ResponseEntity.ok(depositService.calculateInterest(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DepositEntity>> getDepositsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(depositService.getDepositsByUser(userId));
    }
}