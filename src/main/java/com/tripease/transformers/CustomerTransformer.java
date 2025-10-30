package com.tripease.transformers;

import com.tripease.dto.request.CustomerRequest;
import com.tripease.dto.response.CustomerResponse;
import com.tripease.entities.Customer;

public class CustomerTransformer {
    public static Customer customerRequestToCustomer(CustomerRequest customerRequest){
        return Customer.builder().name(customerRequest.getName())
                .age(customerRequest.getAge())
                .email(customerRequest.getEmail())
                .gender(customerRequest.getGender()).
                build();
    }
    public static CustomerResponse customerToCustomerResponse(Customer customer){
        return CustomerResponse.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .email(customer.getEmail())
                .build();
    }

}
