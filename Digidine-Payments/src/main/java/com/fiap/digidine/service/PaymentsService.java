package com.fiap.digidine.service;

import com.fiap.digidine.model.Payment;
import com.fiap.digidine.model.PaymentRequest;

public interface PaymentsService {
    Object getPaymentStatusByOrderNumber(long orderNumber);
    Payment processPayment(PaymentRequest paymentRequest);
}
