package com.fiap.digidine.integration.listener;

import com.fiap.digidine.dto.OrderResponseDTO;
import com.fiap.digidine.dto.PaymentDTO;
import com.fiap.digidine.listener.OrderListener;
import com.fiap.digidine.model.PaymentRequest;
import com.fiap.digidine.service.PaymentsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderListenerTest {

    @InjectMocks
    private OrderListener orderListener;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private PaymentsService paymentsService;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testReceiveOrder_Success() {
        OrderResponseDTO order = new OrderResponseDTO();
        order.setOrderNumber(123L);
        order.setTotalPrice(250.00);

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setOrderNumber(123L);
        paymentDTO.setAmount(250.00);
        paymentDTO.setStatus("PAID");


        when(paymentsService.processPayment(any(PaymentRequest.class))).thenReturn(paymentDTO);

        orderListener.receiveOrder(order);

        verify(paymentsService, times(1)).processPayment(any(PaymentRequest.class));

        verify(rabbitTemplate, times(1)).convertAndSend(
                "digidine.payment.notification",
                "payment.key",
                paymentDTO
        );
    }
}