package com.tripease.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripease.config.KafkaConfig;
import com.tripease.dto.request.LocationUpdateRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class LiveLocationConsumer {
    private final ObjectMapper objectMapper;

    public LiveLocationConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = KafkaConfig.LIVE_LOCATION_TOPIC, groupId = "tripease-location-group")
    public void consume(String message) {
        try {
            LocationUpdateRequest req = objectMapper.readValue(message, LocationUpdateRequest.class);
            System.out.println("Consumed location: driver =" + req.getDriverId() + " lat=" + req.getLatitude() + " long=" + req.getLongitude());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
