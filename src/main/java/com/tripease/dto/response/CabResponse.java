package com.tripease.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CabResponse {
    private String cabNumber;
    private String cabModel;
    private double perKmRate;
    private boolean isAvailable;
    private DriverResponse  driver;
}
