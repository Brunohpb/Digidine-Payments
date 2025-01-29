package com.fiap.digidine.service;

import com.fiap.digidine.model.Payment;
import com.fiap.digidine.repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentsServiceImpl implements PaymentsService {

    @Autowired
    PaymentsRepository paymentsRepository;

    @Override
    public Object getPaymentStatusByOrderNumber(long orderNumber) {
        Payment payment = paymentsRepository.findByOrderNumber(orderNumber);
        if (payment == null) {
            throw new IllegalArgumentException("Order nao possui um payment associado");
        }

        return payment;
    }
}
