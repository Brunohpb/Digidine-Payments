package com.fiap.digidine.mapper;

import com.fiap.digidine.dto.PaymentDTO;
import com.fiap.digidine.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentDTO toPaymentDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setOrderNumber(payment.getOrderNumber());
        paymentDTO.setCreatedAt(payment.getCreatedAt());
        paymentDTO.setStatus(payment.getStatus());

        return paymentDTO;
    }
}
