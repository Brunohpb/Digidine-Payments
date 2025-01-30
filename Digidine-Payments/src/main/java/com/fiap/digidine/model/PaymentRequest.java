package com.fiap.digidine.model;

import java.math.BigDecimal;

public class PaymentRequest {
    private Long orderNumber;
    private BigDecimal totalPrice;

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Getters e Setters
}
