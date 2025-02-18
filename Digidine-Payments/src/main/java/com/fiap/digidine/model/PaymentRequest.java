package com.fiap.digidine.model;

import java.math.BigDecimal;

public class PaymentRequest {
    private Long orderNumber;
    private double totalPrice;

    public PaymentRequest() {

    }

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

    public PaymentRequest(Long orderNumber, double totalPrice) {
        this.orderNumber = orderNumber;
        this.totalPrice = totalPrice;
    }

    // Getters e Setters
}
