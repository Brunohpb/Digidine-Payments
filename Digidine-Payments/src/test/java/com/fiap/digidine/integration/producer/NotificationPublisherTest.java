package com.fiap.digidine.integration.producer;

import com.fiap.digidine.dto.NotificationDTO;
import com.fiap.digidine.dto.PaymentDTO;
import com.fiap.digidine.producer.NotificationPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationPublisherTest {

    @InjectMocks
    private NotificationPublisher notificationPublisher;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Value("${digidine.broker.exchange.paymentNotificationExchange}")
    private String paymentNotificationExchange;

    @Value("${digidine.broker.key.paymentNotificationKey}")
    private String paymentNotificationKey;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testPublishNotificationCommand() {
        NotificationDTO notificationDTO = new NotificationDTO("Payment processed successfully", HttpStatus.OK, new PaymentDTO());

        notificationPublisher.publishNotificationCommand(notificationDTO);

        verify(rabbitTemplate, times(1)).convertAndSend(
                paymentNotificationExchange,
                paymentNotificationKey,
                notificationDTO
        );
    }
}
