package com.tripease.transformers;

import com.tripease.dto.request.DriverRequest;
import com.tripease.dto.response.DriverResponse;
import com.tripease.entities.Driver;

public class DriverTransformer {

    public static Driver requestToDriver(DriverRequest driverRequest){
        return Driver.builder()
                .driverName(driverRequest.getName())
                .driverAge(driverRequest.getAge())
                .driverEmail(driverRequest.getEmail())
                .build();
    }

    public static DriverResponse  driverToDriverResponse(Driver driver){
        return DriverResponse.builder()
                .driverId(driver.getDriverId())
                .name(driver.getDriverName())
                .age(driver.getDriverAge())
                .email(driver.getDriverEmail())
                .build();
    }
}
