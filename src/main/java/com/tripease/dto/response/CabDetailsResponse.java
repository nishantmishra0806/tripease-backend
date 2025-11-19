package com.tripease.dto.response;

import com.tripease.entities.Customer;
import com.tripease.entities.Driver;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CabDetailsResponse {

    private long cabId;
    private String cabNumber;
    private String cabModel;
    private Driver driver;
    private List<Customer> customer;
}
