package com.tripease.repository;

import com.tripease.entities.Cab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CabRepo extends JpaRepository<Cab,Integer> {
    public List<Cab> findByIsAvailable(boolean isAvailable);

    @Query("select c from Cab c where c.isAvailable = true order by  rand() limit 1")
    public Cab getAvailableCabRandomly();
}
