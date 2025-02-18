package com.fiap.digidine.producer;

import com.fiap.digidine.dto.NotificationDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NotificationPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value(value = "${digidine.broker.exchange.paymentNotificationExchange}")
    private String paymentNotificationExchange;

    @Value(value = "${digidine.broker.key.paymentNotificationKey}")
    private String paymentNotificationKey;

    public void publishNotificationCommand(NotificationDTO notificationDTO) {
        rabbitTemplate.convertAndSend(paymentNotificationExchange, paymentNotificationKey, notificationDTO);
    }
}
