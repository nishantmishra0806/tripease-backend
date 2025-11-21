package com.tripease.transformers;

import com.tripease.dto.request.DriverRequest;
import com.tripease.dto.response.DriverResponse;
import com.tripease.entities.Driver;
import com.tripease.security.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DriverTransformer {

    private final PasswordEncoder encoder;
    public DriverTransformer(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public Driver requestToDriver(DriverRequest driverRequest){
        return Driver.builder()
                .driverName(driverRequest.getName())
                .driverAge(driverRequest.getAge())
                .email(driverRequest.getEmail())
                .password(encoder.encode(driverRequest.getPassword()))
                .role(Role.ROLE_DRIVER)
                .build();
    }

    public static DriverResponse  driverToDriverResponse(Driver driver){
        return DriverResponse.builder()
                .driverId(driver.getDriverId())
                .name(driver.getDriverName())
                .age(driver.getDriverAge())
                .email(driver.getEmail())
                .build();
    }
}
