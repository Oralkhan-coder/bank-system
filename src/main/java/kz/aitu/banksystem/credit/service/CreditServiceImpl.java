package kz.aitu.banksystem.credit.service;

import jakarta.persistence.EntityNotFoundException;
import kz.aitu.banksystem.credit.converter.CreditConverter;
import kz.aitu.banksystem.credit.factory.CreditFactory;
import kz.aitu.banksystem.credit.model.CreditStatus;
import kz.aitu.banksystem.credit.model.CreditType;
import kz.aitu.banksystem.credit.model.dto.CreditViewResponseDto;
import kz.aitu.banksystem.credit.model.entity.CreditEntity;
import kz.aitu.banksystem.credit.observer.CreditEvent;
import kz.aitu.banksystem.credit.observer.CreditSubject;
import kz.aitu.banksystem.credit.repository.CreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository;
    private final CreditFactory creditFactory;
    private final CreditConverter creditConverter;
    private final CreditSubject creditSubject;

    @Override
    @Transactional
    public CreditViewResponseDto createCredit(CreditType type, Double amount, Double interestRate, Integer termMonths, Long userId) {
        Double rate = interestRate;
        if (rate == null || rate <= 0) {
            if (type == CreditType.PERSONAL) {
                rate = 15.0;
            } else if (type == CreditType.MORTGAGE) {
                rate = 8.0;
            } else if (type == CreditType.AUTO) {
                rate = 12.0;
            }
        }
        CreditEntity credit = creditFactory.createCredit(type, amount, rate, termMonths, userId);
        creditRepository.save(credit);
        creditSubject.notifyObservers(CreditEvent.CREATED, credit);
        return creditConverter.convert(credit);
    }

    @Override
    @Transactional
    public CreditViewResponseDto makePayment(Long creditId, Double paymentAmount) {
        CreditEntity credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new EntityNotFoundException("Credit not found"));
        if (credit.getStatus() != CreditStatus.ACTIVE) {
            throw new RuntimeException("Credit is not active");
        }

        Double newRemaining = credit.getRemainingAmount() - paymentAmount;
        if (newRemaining < 0) {
            newRemaining = 0.0;
        }
        credit.setRemainingAmount(newRemaining);

        if (newRemaining == 0.0) {
            credit.setStatus(CreditStatus.PAID);
            creditSubject.notifyObservers(CreditEvent.CLOSED, credit);
        } else {
            creditSubject.notifyObservers(CreditEvent.PAYMENT_MADE, credit);
        }
        creditRepository.save(credit);
        return creditConverter.convert(credit);
    }

    @Override
    public CreditViewResponseDto findById(Long id) {
        CreditEntity credit = creditRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Credit not found"));
        return creditConverter.convert(credit);
    }

    @Override
    public List<CreditViewResponseDto> findAllByUserId(Long userId) {
        return creditRepository.findAllByUserId(userId)
                .stream()
                .map(creditConverter::convert)
                .toList();
    }

    @Override
    public List<CreditViewResponseDto> findAll() {
        return creditRepository.findAll()
                .stream()
                .map(creditConverter::convert)
                .toList();
    }

    @Override
    @Transactional
    public void checkOverdueCredits() {
        List<CreditEntity> allCredits = creditRepository.findAll();
        for (CreditEntity credit : allCredits) {
            if (credit.getStatus() == CreditStatus.ACTIVE && credit.getRemainingAmount() > 0) {
                credit.setStatus(CreditStatus.OVERDUE);
                creditRepository.save(credit);
                creditSubject.notifyObservers(CreditEvent.OVERDUE, credit);
            }
        }
    }
}

