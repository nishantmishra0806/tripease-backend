package com.tripease.dto.response;

import com.tripease.enums.TripStatus;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookingResponse {
    private String pickup;

    private String destination;

    private double tripDistanceInKilometers;

    TripStatus tripStatus;

    private double billAmount;

    Date bookedAt;

    Date lastUpdateAt;

    CustomerResponse customer;

    CabResponse cab;

}
