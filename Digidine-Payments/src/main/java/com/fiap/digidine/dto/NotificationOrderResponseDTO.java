package com.fiap.digidine.dto;

import org.springframework.http.HttpStatus;

public class NotificationOrderResponseDTO {
       private String message;
    private HttpStatus httpStatus;
    private OrderResponseDTO orderResponseDTO;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public OrderResponseDTO getOrderResponseDTO() {
        return orderResponseDTO;
    }

    public void setOrderResponseDTO(OrderResponseDTO orderResponseDTO) {
        this.orderResponseDTO = orderResponseDTO;
    }

}
