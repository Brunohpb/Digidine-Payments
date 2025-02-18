package com.fiap.digidine.unit.mapper;

import com.fiap.digidine.dto.PaymentDTO;
import com.fiap.digidine.mapper.PaymentMapper;
import com.fiap.digidine.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentMapperTest {

    private PaymentMapper paymentMapper;

    @BeforeEach
    public void setUp() {
        paymentMapper = new PaymentMapper(); // Inicializando o PaymentMapper antes de cada teste
    }

    @Test
    public void testToPaymentDTO() {
        // Criando um objeto Payment de exemplo
        Payment payment = new Payment();
        payment.setAmount(100.0);
        payment.setOrderNumber(123L);
        payment.setCreatedAt(LocalDateTime.now());
        payment.setStatus("PAID");

        // Chamando o método a ser testado
        PaymentDTO paymentDTO = paymentMapper.toPaymentDTO(payment);

        // Verificando se os valores foram mapeados corretamente
        assertEquals(payment.getAmount(), paymentDTO.getAmount());
        assertEquals(payment.getOrderNumber(), paymentDTO.getOrderNumber());
        assertEquals(payment.getCreatedAt(), paymentDTO.getCreatedAt());
        assertEquals(payment.getStatus(), paymentDTO.getStatus());
    }
}
