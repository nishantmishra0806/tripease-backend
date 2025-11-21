package com.tripease.services;

import com.tripease.dto.request.CustomerRequest;
import com.tripease.dto.response.CustomerResponse;
import com.tripease.entities.Customer;
import com.tripease.enums.Gender;
import com.tripease.exceptions.CustomerNotFoundException;
import com.tripease.repository.CustomerRepo;
import com.tripease.transformers.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    PasswordEncoder encoder;

    public CustomerResponse register(CustomerRequest customerRequest) {
        //ReqDTO --> entity conversion
        CustomerTransformer transformer = new CustomerTransformer(encoder);
        Customer customer = transformer.customerRequestToCustomer(customerRequest);
        //save entity to DB
        Customer savedCustomer= customerRepo.save(customer);
        //entity --> ResponseDTO conversion
        CustomerResponse customerResponse = CustomerTransformer.customerToCustomerResponse(savedCustomer);

        return  customerResponse;
    }

    public CustomerResponse getCustomerById(Integer id){
        Optional<Customer> customer = customerRepo.findById(id);
        if(customer.isEmpty()){
            throw new CustomerNotFoundException("Invalid Customer Id");
        }
        Customer savedCustomer= customer.get();
        // entity --> CustomerResponseDTO conversion
        CustomerResponse customerResponse = CustomerTransformer.customerToCustomerResponse(savedCustomer);

        return  customerResponse;
    }

    public List<CustomerResponse> getAllByGender(Gender gender){
        List<Customer> customers = customerRepo.findByGender(gender);

        // entity to response dto
        List<CustomerResponse> responses = new ArrayList<>();
        for(Customer customer : customers){
            CustomerResponse customerResponse = CustomerTransformer.customerToCustomerResponse(customer);
            responses.add(customerResponse);
        }
        return responses;
    }

    public List<CustomerResponse> getCustomerByGenderAndAgeGreaterThan(Gender gender, int age){
        List<Customer> customers = customerRepo.findByGenderAndAgeGreaterThan(gender,age);
        List<CustomerResponse> responses = new ArrayList<>();
        for(Customer customer : customers){
            CustomerResponse customerResponse = CustomerTransformer.customerToCustomerResponse(customer);
            responses.add(customerResponse);
        }
        return responses;
    }
}
