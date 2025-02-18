package com.fiap.digidine.dto;

import org.springframework.http.HttpStatus;

public record NotificationDTO (
        String message,
        HttpStatus httpStatus,
        PaymentDTO paymentDTO)
{

    public NotificationDTO(String message, HttpStatus httpStatus, PaymentDTO paymentDTO) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.paymentDTO = paymentDTO;
    }
}
