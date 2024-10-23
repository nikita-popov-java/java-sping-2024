package ru.nikitapopov.skillbox.mod6.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusEvent {
    private String status;
    private Instant date;
}