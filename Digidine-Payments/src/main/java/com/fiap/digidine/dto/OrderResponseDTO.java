package com.fiap.digidine.dto;

import com.fiap.digidine.model.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDTO {

    private Long orderNumber;
    private CustomerRequestDTO customer;
    private List<ProductRequestDTO> products;
    private double totalPrice;
    private OrderStatus orderStatus;
    private LocalDateTime createdAt;

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public CustomerRequestDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerRequestDTO customer) {
        this.customer = customer;
    }

    public List<ProductRequestDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductRequestDTO> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
