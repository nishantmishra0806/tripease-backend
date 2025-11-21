package com.tripease.controllers;

import com.tripease.dto.request.CustomerRequest;
import com.tripease.dto.request.DriverRequest;
import com.tripease.dto.response.CustomerResponse;
import com.tripease.dto.response.DriverResponse;
import com.tripease.entities.Customer;
import com.tripease.entities.Driver;
import com.tripease.repository.CustomerRepo;
import com.tripease.repository.DriverRepo;
import com.tripease.security.Role;
import com.tripease.services.CustomerService;
import com.tripease.services.DriverService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private CustomerService  customerService;
    private DriverService driverService;

    AuthController(CustomerService customerService, DriverService driverService) {
        this.customerService = customerService;
        this.driverService = driverService;
    }

    @PostMapping("/register/customer")
    public CustomerResponse registerCustomer(@RequestBody CustomerRequest customer) {
        return customerService.register(customer);
    }

    @PostMapping("/register/driver")
    public DriverResponse registerDriver(@RequestBody DriverRequest driver) {
        return driverService.register(driver);
    }
}
