package ru.nikitapopov.skillbox.mod6.orderstatusservice.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.nikitapopov.skillbox.mod6.dto.OrderEvent;
import ru.nikitapopov.skillbox.mod6.dto.OrderStatusEvent;

import java.time.Instant;

@Service
public class OrderListener {

    private final KafkaTemplate<String, OrderStatusEvent> kafkaTemplate;

    public OrderListener(KafkaTemplate<String, OrderStatusEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "order-topic", groupId = "order-status-service-group")
    public void listenOrderTopic(OrderEvent orderEvent) {
        OrderStatusEvent statusEvent = new OrderStatusEvent("CREATED", Instant.now());
        kafkaTemplate.send("order-status-topic", statusEvent);
    }
}