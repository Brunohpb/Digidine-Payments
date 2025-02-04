package com.fiap.digidine.controller;

import com.fiap.digidine.dto.PaymentDTO;
import com.fiap.digidine.model.Payment;
import com.fiap.digidine.model.PaymentRequest;
import com.fiap.digidine.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentsController {

    @Autowired
    private PaymentsService paymentsService;

    @GetMapping("/orders/{orderNumber}")
    public ResponseEntity<Object> getPaymentsStatusByOrderId(@PathVariable long orderNumber) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(paymentsService.getPaymentStatusByOrderNumber(orderNumber));
        } catch (IllegalArgumentException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro:" + e.getMessage());
        }
    }

    @PostMapping
    public PaymentDTO processPayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentsService.processPayment(paymentRequest);
    }

}
