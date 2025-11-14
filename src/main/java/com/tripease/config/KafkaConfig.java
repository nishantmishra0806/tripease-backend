package com.tripease.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    public static final String LIVE_LOCATION_TOPIC = "live-location-updates";

    @Bean
    public NewTopic liveLocationTopic() {
        return TopicBuilder.name(LIVE_LOCATION_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
