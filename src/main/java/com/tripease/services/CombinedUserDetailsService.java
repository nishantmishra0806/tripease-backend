package com.tripease.services;

import com.tripease.repository.CustomerRepo;
import com.tripease.repository.DriverRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CombinedUserDetailsService implements UserDetailsService {

    private CustomerRepo customerRepo;
    private DriverRepo driverRepo;

    CombinedUserDetailsService(CustomerRepo customerRepo, DriverRepo driverRepo) {
        this.customerRepo = customerRepo;
        this.driverRepo = driverRepo;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var customer = customerRepo.findByEmail(email).orElse(null);
        if (customer != null) {
            return User.withUsername(customer.getEmail())
                    .password(customer.getPassword())
                    .roles(customer.getRole().name().replace("ROLE_", ""))
                    .build();
        }

        // Check Driver
        var driver = driverRepo.findByEmail(email).orElse(null);
        if (driver != null) {
            return User.withUsername(driver.getEmail())
                    .password(driver.getPassword())
                    .roles(driver.getRole().name().replace("ROLE_", ""))
                    .build();
        }

        throw new UsernameNotFoundException("User not found");
    }


}
