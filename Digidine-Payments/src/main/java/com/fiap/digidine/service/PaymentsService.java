package com.fiap.digidine.service;

import com.fiap.digidine.dto.PaymentDTO;
import com.fiap.digidine.model.Payment;
import com.fiap.digidine.model.PaymentRequest;

public interface PaymentsService {
    PaymentDTO getPaymentStatusByOrderNumber(long orderNumber);
    PaymentDTO processPayment(PaymentRequest paymentRequest);
}
