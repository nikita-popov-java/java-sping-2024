package ru.nikitapopov.skillbox.mod6.orderservice.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderStatusListener {

    @KafkaListener(topics = "order-status-topic", groupId = "order-service-group")
    public void listenOrderStatus(ConsumerRecord<String, String> record) {
        log.info("Received message: {}", record.value());
        log.info("Key: {}; Partition: {}; Topic: {}, Timestamp: {}",
                record.key(), record.partition(), record.topic(), record.timestamp());
    }
}