package com.tripease.transformers;

import com.tripease.dto.request.BookingRequest;
import com.tripease.dto.response.BookingResponse;
import com.tripease.entities.Booking;
import com.tripease.entities.Cab;
import com.tripease.entities.Customer;
import com.tripease.entities.Driver;
import com.tripease.enums.TripStatus;

public class BookingTransformer {
    public static Booking bookingReqToBooking(BookingRequest bookingRequest , double perKmRate){
        return Booking.builder()
                .pickup(bookingRequest.getPickup())
                .destination(bookingRequest.getDestination())
                .tripDistanceInKilometers(bookingRequest.getTripDistInKm())
                .tripStatus(TripStatus.InProgress)
                .billAmount(bookingRequest.getTripDistInKm()*perKmRate)
                .build();
    }

    public static BookingResponse bookingToBookResponse(Booking booking , Customer customer , Cab cab , Driver driver ){
        return BookingResponse.builder()
                .pickup(booking.getPickup())
                .destination(booking.getDestination())
                .tripDistanceInKilometers(booking.getTripDistanceInKilometers())
                .tripStatus(booking.getTripStatus())
                .billAmount(booking.getBillAmount())
                .bookedAt(booking.getBookedAt())
                .lastUpdateAt(booking.getLastUpdateAt())
                .customer(CustomerTransformer.customerToCustomerResponse(customer))
                .cab(CabTransformer.cabToCabResponse(cab,driver))
                .build();
    }
}
