package com.tripease.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripease.config.KafkaConfig;
import com.tripease.dto.request.LocationUpdateRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LiveLocationProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public LiveLocationProducer(KafkaTemplate<String, String> kafkaTemplate,
                                ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void send(LocationUpdateRequest req) {
        try {
            String payload = objectMapper.writeValueAsString(req);
            // use agentId as key so the same agent goes to same partition
            String key = req.getDriverId() == null ? "unknown" : String.valueOf(req.getDriverId());
            kafkaTemplate.send(KafkaConfig.LIVE_LOCATION_TOPIC, key, payload);
        } catch (JsonProcessingException e) {
            // log and swallow or rethrow depending on your logging strategy
            e.printStackTrace();
        }
    }
}
