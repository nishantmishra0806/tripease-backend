package com.tripease.services;

import com.tripease.dto.request.CabRequest;
import com.tripease.dto.response.CabResponse;
import com.tripease.entities.Cab;
import com.tripease.entities.Driver;
import com.tripease.exceptions.DriverNotFoundException;
import com.tripease.repository.CabRepo;
import com.tripease.repository.DriverRepo;
import com.tripease.transformers.CabTransformer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CabService {


    private DriverRepo driverRepo;
    public CabService(  DriverRepo driverRepo) {
        this.driverRepo = driverRepo;
    }

    public CabResponse registerCab(CabRequest  cabRequest , int driverId) {
        //checking whether driver is valid or not
        Optional<Driver> optionalDriver = driverRepo.findById(driverId);
        if(optionalDriver.isEmpty()){
            throw new DriverNotFoundException("Driver not found");
        }
        //if valid
        Driver driver = optionalDriver.get();
        Cab cab = CabTransformer.cabReqToCab(cabRequest);
        driver.setCab(cab);
        //we are saving driver and we have use CASCADEALL which will automatically saved into child entity
        Driver savedDriver = driverRepo.save(driver);
        return CabTransformer.cabToCabResponse(savedDriver.getCab(), savedDriver) ;
    }
}
