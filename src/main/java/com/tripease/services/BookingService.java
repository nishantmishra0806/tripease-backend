package com.tripease.services;

import com.tripease.dto.request.BookingRequest;
import com.tripease.dto.response.BookingResponse;
import com.tripease.entities.Booking;
import com.tripease.entities.Cab;
import com.tripease.entities.Customer;
import com.tripease.entities.Driver;
import com.tripease.exceptions.CabUnavailableException;
import com.tripease.exceptions.CustomerNotFoundException;
import com.tripease.repository.BookingRepo;
import com.tripease.repository.CabRepo;
import com.tripease.repository.CustomerRepo;
import com.tripease.repository.DriverRepo;
import com.tripease.transformers.BookingTransformer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class BookingService {
    private final JavaMailSender javaMailSender;
    CabRepo  cabRepo;
    BookingRepo bookingRepo;
    CustomerRepo customerRepo;
    DriverRepo driverRepo;
    JavaMailSender mailSender;
    BookingService(CabRepo cabRepo, BookingRepo bookingRepo , CustomerRepo customerRepo , DriverRepo  driverRepo, JavaMailSender javaMailSender) {
        this.cabRepo = cabRepo;
        this.bookingRepo = bookingRepo;
        this.customerRepo = customerRepo;
        this.driverRepo =  driverRepo;
        this.javaMailSender = javaMailSender;
    }
    public BookingResponse bookCab(BookingRequest bookingRequest,int customerId){
        Optional<Customer> customer = customerRepo.findById(customerId);
        if(customer.isEmpty()){
            throw new CustomerNotFoundException("Customer not found");
        }

        Cab availableCab = cabRepo.getAvailableCabRandomly();
        if(availableCab == null){
            throw new CabUnavailableException("Sorry! No cab available at the moment");
        }
        double perKmRate = availableCab.getPerKmRate();
        //bookingReq --> booking Entity
        Booking booking = BookingTransformer.bookingReqToBooking(bookingRequest,perKmRate);
        Booking savedBooking = bookingRepo.save(booking);

        availableCab.setAvailable(false);
        customer.get().getBookings().add(savedBooking);

        Driver driver = driverRepo.findDriverByCabId(availableCab.getCabId());
        driver.getBookings().add(savedBooking);
        //This will automatically propagated to booking as customer and driver are parent entity of bookings
        Customer savedCustomer = customerRepo.save(customer.get());
        Driver savedDriver = driverRepo.save(driver);

        String text = "Congrats" + savedCustomer.getName() + ", your cab has been booked! from " + savedBooking.getPickup()+ " to " +  savedBooking.getDestination();
        sendEmail(savedCustomer , text);
        // Booking to BookingResponse
        return BookingTransformer.bookingToBookResponse(savedBooking , savedCustomer , availableCab , savedDriver);
    }
    private void sendEmail(Customer savedCustomer , String text){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("tripease0806@gmail.com");
        simpleMailMessage.setTo(savedCustomer.getEmail());
        simpleMailMessage.setSubject("Booking");
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
    }
}
