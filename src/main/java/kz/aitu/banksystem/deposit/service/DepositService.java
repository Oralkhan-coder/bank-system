package kz.aitu.banksystem.deposit.service;

import kz.aitu.banksystem.deposit.factory.DepositFactory;
import kz.aitu.banksystem.deposit.model.DepositType;
import kz.aitu.banksystem.deposit.model.entity.DepositEntity;
import kz.aitu.banksystem.deposit.repository.DepositRepository;
import kz.aitu.banksystem.deposit.strategy.FixedDepositStrategy;
import kz.aitu.banksystem.deposit.strategy.SavingsDepositStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepositService {

    private final DepositRepository depositRepository;
    private final DepositFactory depositFactory;
    private final FixedDepositStrategy fixedStrategy;
    private final SavingsDepositStrategy savingsStrategy;

    public DepositEntity createDeposit(DepositType type, Double amount, Double rate, Long userId, Integer months) {
        DepositEntity deposit = depositFactory.createDeposit(type, amount, rate, userId, months);
        return depositRepository.save(deposit);
    }

    public double calculateInterest(Long depositId) {
        DepositEntity deposit = depositRepository.findById(depositId)
                .orElseThrow(() -> new RuntimeException("Deposit not found"));

        switch (deposit.getType()) {
            case FIXED:
                return fixedStrategy.calculate(deposit.getInitialAmount(), deposit.getInterestRate(), deposit.getDurationMonths());
            case SAVINGS:
                return savingsStrategy.calculate(deposit.getInitialAmount(), deposit.getInterestRate(), deposit.getDurationMonths());
            default:
                throw new RuntimeException("Unknown deposit type");
        }
    }

    public List<DepositEntity> getDepositsByUser(Long userId) {
        return depositRepository.findAllByUserId(userId);
    }
}