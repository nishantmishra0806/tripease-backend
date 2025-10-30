package com.tripease.repository;

import com.tripease.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Integer> {

    @Query(value = "select * from driver where cab_cab_id = :cabId" ,nativeQuery = true)
    Driver findDriverByCabId(@Param("cabId") int id);
}
