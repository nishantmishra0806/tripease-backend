package com.tripease.services;

import com.tripease.dto.request.DriverRequest;
import com.tripease.dto.response.DriverResponse;
import com.tripease.entities.Driver;
import com.tripease.repository.DriverRepo;
import com.tripease.transformers.DriverTransformer;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
    DriverRepo driverRepo;
    public DriverService(DriverRepo driverRepo) {
        this.driverRepo = driverRepo;
    }

    public DriverResponse addDriver(DriverRequest driverRequest){
        //request-> entity transformation
        Driver driver = DriverTransformer.requestToDriver(driverRequest);
        Driver savedDriver = driverRepo.save(driver);
        return DriverTransformer.driverToDriverResponse(savedDriver);
    }
}
