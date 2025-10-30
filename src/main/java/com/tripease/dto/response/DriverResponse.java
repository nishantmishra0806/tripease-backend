package com.tripease.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DriverResponse {
    private int  driverId;
    private String name;
    private int age;
    private String email;
}
