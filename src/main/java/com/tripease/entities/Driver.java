package com.tripease.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int driverId;

    private String driverName;

    private int driverAge;

    @Column(unique = true, nullable = false)
    private String driverEmail;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    List<Booking> bookings = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    Cab cab;
}

