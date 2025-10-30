package com.tripease.entities;

import com.tripease.enums.TripStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    private String pickup;

    private String destination;

    private double tripDistanceInKilometers;

    @Enumerated(EnumType.STRING)
    TripStatus tripStatus;

    private double billAmount;

    @CreationTimestamp
    Date bookedAt;

    @UpdateTimestamp
    Date lastUpdateAt;
}
