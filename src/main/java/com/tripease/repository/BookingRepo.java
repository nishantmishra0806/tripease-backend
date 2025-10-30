package com.tripease.repository;

import com.tripease.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {
}
