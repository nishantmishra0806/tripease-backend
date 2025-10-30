package com.tripease.repository;

import com.tripease.entities.Customer;
import com.tripease.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    List<Customer>findByGender(Gender gender);
    @Query("select c from Customer c where c.gender = :gender AND c.age > :age")
    List<Customer>findByGenderAndAgeGreaterThan(@Param("gender") Gender gender, @Param("age") int age);
}
