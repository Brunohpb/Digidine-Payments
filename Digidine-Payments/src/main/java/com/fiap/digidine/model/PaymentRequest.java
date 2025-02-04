package com.fiap.digidine.model;

import java.math.BigDecimal;

public class PaymentRequest {
    private Long orderNumber;
    private double totalPrice;

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Getters e Setters
}
