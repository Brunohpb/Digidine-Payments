package com.fiap.digidine.service;

import com.fiap.digidine.model.Payment;
import com.fiap.digidine.model.PaymentRequest;
import com.fiap.digidine.repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentsServiceImpl implements PaymentsService {

    @Autowired
    PaymentsRepository paymentsRepository;

    @Override
    public Payment processPayment(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setOrderNumber(paymentRequest.getOrderNumber());
        payment.setAmount(paymentRequest.getTotalPrice());
        payment.setCreatedAt(LocalDateTime.now());
        payment.setStatus("PAID");

        return paymentsRepository.save(payment);
    }

    @Override
    public Object getPaymentStatusByOrderNumber(long orderNumber) {
        Payment payment = paymentsRepository.findByOrderNumber(orderNumber);
        if (payment == null) {
            throw new IllegalArgumentException("Order nao possui um payment associado");
        }

        return payment;
    }
}
