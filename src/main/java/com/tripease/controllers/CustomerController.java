package com.tripease.controllers;

import com.tripease.dto.request.CustomerRequest;
import com.tripease.dto.response.CustomerResponse;
import com.tripease.enums.Gender;
import com.tripease.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer") //base mapping
public class CustomerController {

    private final CustomerService customerService;

    CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }


    @GetMapping("/get/customer-id/{id}")
    public CustomerResponse getCustomer(@PathVariable Integer id){
        return customerService.getCustomerById(id);
    }

    @GetMapping("/getby/gender/{gender}")
    public List<CustomerResponse> getAllByGender(@PathVariable Gender gender){
        return customerService.getAllByGender(gender);
    }

    @GetMapping("/getby/gender-and-age")
    public List<CustomerResponse> getCustomerByGenderAndAgeGreaterThan(@RequestParam Gender gender , @RequestParam int age){
        return customerService.getCustomerByGenderAndAgeGreaterThan(gender,age);
    }
}