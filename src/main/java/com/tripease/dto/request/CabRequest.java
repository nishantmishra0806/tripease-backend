package com.tripease.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CabRequest {
    private String cabNumber;
    private String cabModel;
    private double perKmRate;
}
