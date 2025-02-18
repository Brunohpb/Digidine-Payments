package com.fiap.digidine.unit.service;

import com.fiap.digidine.dto.PaymentDTO;
import com.fiap.digidine.dto.NotificationDTO;
import com.fiap.digidine.mapper.PaymentMapper;
import com.fiap.digidine.model.Payment;
import com.fiap.digidine.model.PaymentRequest;
import com.fiap.digidine.producer.NotificationPublisher;
import com.fiap.digidine.repository.PaymentsRepository;
import com.fiap.digidine.service.PaymentsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PaymentsServiceImplTest {

    @Mock
    private PaymentsRepository paymentsRepository;

    @Mock
    private NotificationPublisher notificationPublisher;

    @Mock
    private PaymentMapper paymentMapper;

    @InjectMocks
    private PaymentsServiceImpl paymentsService;

    private PaymentRequest paymentRequest;

    @BeforeEach
    void setUp() {
        paymentRequest = new PaymentRequest();
        paymentRequest.setOrderNumber(12345L);
        paymentRequest.setTotalPrice(200.0);
    }

    @Test
    void shouldProcessPaymentAndReturnPaymentDTO() {
        // Arrange
        Payment payment = new Payment();
        payment.setOrderNumber(paymentRequest.getOrderNumber());
        payment.setAmount(paymentRequest.getTotalPrice());
        payment.setCreatedAt(LocalDateTime.now());
        payment.setStatus("PAID");

        PaymentDTO paymentDTO = new PaymentDTO(payment.getOrderNumber(), payment.getAmount(), payment.getStatus());

        when(paymentsRepository.save(any(Payment.class))).thenReturn(payment);
        when(paymentMapper.toPaymentDTO(payment)).thenReturn(paymentDTO);

        // Act
        PaymentDTO result = paymentsService.processPayment(paymentRequest);

        // Assert
        assertNotNull(result);
        assertEquals(paymentRequest.getOrderNumber(), result.getOrderNumber());
        assertEquals(paymentRequest.getTotalPrice(), result.getAmount());
        assertEquals("PAID", result.getStatus());

        verify(notificationPublisher, times(1)).publishNotificationCommand(any(NotificationDTO.class));
    }

    @Test
    void shouldReturnPaymentStatusByOrderNumber() {
        // Arrange
        Payment payment = new Payment();
        payment.setOrderNumber(12345L);
        payment.setAmount(200.0);
        payment.setCreatedAt(LocalDateTime.now());
        payment.setStatus("PAID");

        PaymentDTO paymentDTO = new PaymentDTO(payment.getOrderNumber(), payment.getAmount(), payment.getStatus());

        when(paymentsRepository.findByOrderNumber(payment.getOrderNumber())).thenReturn(payment);
        when(paymentMapper.toPaymentDTO(payment)).thenReturn(paymentDTO);

        // Act
        PaymentDTO result = paymentsService.getPaymentStatusByOrderNumber(payment.getOrderNumber());

        // Assert
        assertNotNull(result);
        assertEquals(payment.getOrderNumber(), result.getOrderNumber());
        assertEquals(payment.getAmount(), result.getAmount());
        assertEquals(payment.getStatus(), result.getStatus());
    }

    @Test
    void shouldThrowExceptionWhenPaymentNotFound() {
        // Arrange
        long nonExistentOrderNumber = 99999L;
        when(paymentsRepository.findByOrderNumber(nonExistentOrderNumber)).thenReturn(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            paymentsService.getPaymentStatusByOrderNumber(nonExistentOrderNumber);
        });

        assertEquals("Order nao possui um payment associado", exception.getMessage());
    }
}
