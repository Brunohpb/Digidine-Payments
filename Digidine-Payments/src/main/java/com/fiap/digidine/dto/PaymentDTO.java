package com.fiap.digidine.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentDTO {

    private Long orderNumber;
    private double amount;
    private LocalDateTime createdAt;
    private String status;

    public PaymentDTO(Long orderNumber, double amount, String status) {
        this.orderNumber = orderNumber;
        this.amount = amount;
        this.status = status;
    }

    public PaymentDTO() {

    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
