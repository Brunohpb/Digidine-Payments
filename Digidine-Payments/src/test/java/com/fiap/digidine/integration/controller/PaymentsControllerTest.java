package com.fiap.digidine.integration.controller;

import com.fiap.digidine.controller.PaymentsController;
import com.fiap.digidine.dto.PaymentDTO;
import com.fiap.digidine.model.PaymentRequest;
import com.fiap.digidine.service.PaymentsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PaymentsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PaymentsService paymentsService;

    @InjectMocks
    private PaymentsController paymentsController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(paymentsController).build();
    }

    @Test
    public void testGetPaymentsStatusByOrderId_Success() throws Exception {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setOrderNumber(1L);
        paymentDTO.setAmount(100.00);
        paymentDTO.setStatus("PAID");

        when(paymentsService.getPaymentStatusByOrderNumber(1L)).thenReturn(paymentDTO);
        mockMvc.perform(get("/api/v1/payments/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderNumber").value(1L))
                .andExpect(jsonPath("$.amount").value(100.00))
                .andExpect(jsonPath("$.status").value("PAID"));

        verify(paymentsService, times(1)).getPaymentStatusByOrderNumber(1L);
    }

    @Test
    public void testGetPaymentsStatusByOrderId_BadRequest() throws Exception {
        when(paymentsService.getPaymentStatusByOrderNumber(999L)).thenThrow(new IllegalArgumentException("Order não possui um payment associado"));

        mockMvc.perform(get("/api/v1/payments/orders/999"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Erro:Order não possui um payment associado"));

        verify(paymentsService, times(1)).getPaymentStatusByOrderNumber(999L);
    }

    @Test
    public void testProcessPayment_Success() throws Exception {
        PaymentRequest paymentRequest = new PaymentRequest(1L, 100.00);
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setOrderNumber(1L);
        paymentDTO.setAmount(100.00);
        paymentDTO.setStatus("PAID");

        when(paymentsService.processPayment(any(PaymentRequest.class))).thenReturn(paymentDTO);

        mockMvc.perform(post("/api/v1/payments")
                        .contentType("application/json")
                        .content("{\"orderNumber\": 1, \"totalPrice\": 100.00}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderNumber").value(1L))
                .andExpect(jsonPath("$.amount").value(100.00))
                .andExpect(jsonPath("$.status").value("PAID"));

        verify(paymentsService, times(1)).processPayment(any(PaymentRequest.class));
    }
}
