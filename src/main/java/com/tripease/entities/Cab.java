package com.tripease.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Cab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cabId;
    private String cabModel;
    private String cabNumber;
    private double perKmRate;
    private boolean isAvailable;


}

