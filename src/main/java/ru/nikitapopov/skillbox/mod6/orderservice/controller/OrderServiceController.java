package ru.nikitapopov.skillbox.mod6.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikitapopov.skillbox.mod6.dto.OrderEvent;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderServiceController {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @PostMapping
    public String createOrder(@RequestBody OrderEvent orderEvent) {
        kafkaTemplate.send("order-topic", orderEvent);
        return "Order sent to Kafka";
    }
}
