package com.fiap.digidine.integration.repository;

import com.fiap.digidine.model.Payment;
import com.fiap.digidine.repository.PaymentsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PaymentsRepositoryTest {

    @Mock
    private PaymentsRepository paymentsRepository;

    @Test
    public void testFindByOrderNumber() {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setOrderNumber(123L);
        payment.setAmount(100.00);
        payment.setStatus("PAID");

        when(paymentsRepository.findByOrderNumber(123L)).thenReturn(payment);

        Payment result = paymentsRepository.findByOrderNumber(123L);

        assertNotNull(result);
        assertEquals(123L, result.getOrderNumber());
        assertEquals(100.00, result.getAmount());
        assertEquals("PAID", result.getStatus());

        verify(paymentsRepository, times(1)).findByOrderNumber(123L);
    }
}

