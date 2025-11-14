package com.tripease.dto.request;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationUpdateRequest {
    private Long driverId; // driver id or user id
    private Double latitude;
    private Double longitude;
    private Instant timestamp;
}
