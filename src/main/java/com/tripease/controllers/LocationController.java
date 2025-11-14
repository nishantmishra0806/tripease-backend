package com.tripease.controllers;

import com.tripease.dto.request.LocationUpdateRequest;
import com.tripease.services.LiveLocationProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    private final LiveLocationProducer producer;

    public LocationController(LiveLocationProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateLocation(@RequestBody LocationUpdateRequest request) {
        // basic validation
        if (request.getDriverId() == null || request.getLatitude() == null || request.getLongitude() == null) {
            return ResponseEntity.badRequest().body("agentId, latitude and longitude are required");
        }
        producer.send(request);
        return ResponseEntity.ok("Location received");
    }
}
