package com.fiap.digidine.listener;

import com.fiap.digidine.dto.OrderResponseDTO;
import com.fiap.digidine.dto.PaymentDTO;
import com.fiap.digidine.model.PaymentRequest;
import com.fiap.digidine.service.PaymentsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    PaymentsService paymentsService;

    @RabbitListener(queues = "${digidine.broker.queue.order}")
    public void receiveOrder(OrderResponseDTO order) {
        System.out.println("Received order: " + order);

        PaymentRequest payment = new PaymentRequest();
        payment.setOrderNumber(order.getOrderNumber());
        payment.setTotalPrice(order.getTotalPrice());
        // Processa o pedido e cria uma mensagem de pagamento
        PaymentDTO paymentRequest = paymentsService.processPayment(payment);

        // Publica a mensagem na fila de pagamentos
        rabbitTemplate.convertAndSend("digidine.payment.notification", "payment.key", paymentRequest);
        System.out.println("Payment request sent: " + paymentRequest);
    }
}
