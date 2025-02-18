package com.fiap.digidine.service;

import com.fiap.digidine.dto.NotificationDTO;
import com.fiap.digidine.dto.PaymentDTO;
import com.fiap.digidine.mapper.PaymentMapper;
import com.fiap.digidine.model.Payment;
import com.fiap.digidine.model.PaymentRequest;
import com.fiap.digidine.producer.NotificationPublisher;
import com.fiap.digidine.repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Service
public class PaymentsServiceImpl implements PaymentsService {

    @Autowired
    PaymentsRepository paymentsRepository;

    @Autowired
    NotificationPublisher notificationPublisher;

    @Autowired
    PaymentMapper paymentMapper;

    @Override
    public PaymentDTO processPayment(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setOrderNumber(paymentRequest.getOrderNumber());
        payment.setAmount(paymentRequest.getTotalPrice());
        payment.setCreatedAt(LocalDateTime.now());
        payment.setStatus("PAID");

        Payment paymentSaved = paymentsRepository.save(payment);
        PaymentDTO paymentDTO = paymentMapper.toPaymentDTO(paymentSaved);

        notificationPublisher.publishNotificationCommand(new NotificationDTO(
                "Payment created: " + paymentSaved.getOrderNumber(),
                HttpStatus.OK,
                paymentDTO));

        return paymentDTO;
    }

    @Override
    public PaymentDTO getPaymentStatusByOrderNumber(long orderNumber) {
        Payment payment = paymentsRepository.findByOrderNumber(orderNumber);
        if (payment == null) {
            throw new IllegalArgumentException("Order nao possui um payment associado");
        }

        return paymentMapper.toPaymentDTO(payment);
    }
}
