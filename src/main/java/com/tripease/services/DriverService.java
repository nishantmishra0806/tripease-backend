package com.tripease.services;

import com.tripease.dto.request.DriverRequest;
import com.tripease.dto.response.DriverResponse;
import com.tripease.entities.Driver;
import com.tripease.repository.DriverRepo;
import com.tripease.transformers.DriverTransformer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
    PasswordEncoder encoder;
    DriverRepo driverRepo;
    public DriverService(DriverRepo driverRepo , PasswordEncoder encoder) {
        this.driverRepo = driverRepo;
        this.encoder = encoder;
    }

    public DriverResponse register(DriverRequest driverRequest){
        //request-> entity transformation
        DriverTransformer transformer = new DriverTransformer(encoder);
        Driver driver = transformer.requestToDriver(driverRequest);
        Driver savedDriver = driverRepo.save(driver);
        return DriverTransformer.driverToDriverResponse(savedDriver);
    }
}
