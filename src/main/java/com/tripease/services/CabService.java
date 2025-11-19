package com.tripease.services;

import com.tripease.dto.request.CabRequest;
import com.tripease.dto.response.CabDetailsResponse;
import com.tripease.dto.response.CabResponse;
import com.tripease.entities.Booking;
import com.tripease.entities.Cab;
import com.tripease.entities.Customer;
import com.tripease.entities.Driver;
import com.tripease.exceptions.DriverNotFoundException;
import com.tripease.repository.CabRepo;
import com.tripease.repository.DriverRepo;
import com.tripease.transformers.CabTransformer;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CabService {


    private DriverRepo driverRepo;
    private CabRepo cabRepo;
    public CabService(  DriverRepo driverRepo , CabRepo cabRepo) {
        this.driverRepo = driverRepo;
        this.cabRepo = cabRepo;
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

    public CabDetailsResponse getCabDetails(@RequestParam int cabId) {
        Optional<Driver> optionalDriver = driverRepo.findById(cabId);
        if(optionalDriver.isEmpty()){
            throw new DriverNotFoundException("Driver not found");
        }
        Driver driver = optionalDriver.get();
        Optional<Cab> optionalCab = cabRepo.findById(cabId);
        if(optionalCab.isEmpty()){
            throw new RuntimeException("Cab not found");
        }
        Cab cab = optionalCab.get();

        List<Booking> book = driver.getBookings();
        List<Customer> cust = new ArrayList<>();
        for(Booking booking : book){
            cust.add(booking.getCustomer());
        }

        CabDetailsResponse response = CabDetailsResponse.builder()
                .cabId(cabId)
                .cabModel(cab.getCabModel())
                .cabNumber(cab.getCabNumber())
                .driver(driver)
                .customer(cust).build();


        return response;
    }
}
