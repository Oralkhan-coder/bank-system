package kz.aitu.banksystem.payment.adapter;

import kz.aitu.banksystem.payment.client.GooglePayClient;
import kz.aitu.banksystem.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GooglePayAdapter implements GooglePayClient  {
    private final PaymentService paymentService;


    @Override
    @Transactional
    public void payByGoogle(Double amount, String phoneNumber) {
        log.info("Google Payment {}", amount);
        paymentService.pay(amount, phoneNumber);
    }
}