package com.tripease.transformers;

import com.tripease.dto.request.CustomerRequest;
import com.tripease.dto.response.CustomerResponse;
import com.tripease.entities.Customer;
import com.tripease.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomerTransformer {

    private final PasswordEncoder encoder ;
    public CustomerTransformer(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public Customer customerRequestToCustomer(CustomerRequest customerRequest){
        return Customer.builder().name(customerRequest.getName())
                .age(customerRequest.getAge())
                .email(customerRequest.getEmail())
                .password(encoder.encode(customerRequest.getPassword()))
                .role(Role.ROLE_CUSTOMER)
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
