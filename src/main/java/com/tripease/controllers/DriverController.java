package com.tripease.controllers;

import com.tripease.dto.request.DriverRequest;
import com.tripease.dto.response.DriverResponse;
import com.tripease.services.DriverService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver")
public class DriverController {

    private DriverService driverService;
    DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/add")
   public DriverResponse addDriver(@RequestBody DriverRequest driverRequest){
       return driverService.addDriver(driverRequest);
   }

}
