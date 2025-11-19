package com.tripease.controllers;

import com.tripease.dto.request.BookingRequest;
import com.tripease.dto.response.BookingResponse;
import com.tripease.services.BookingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {
    BookingService bookingService ;
    public BookingController(BookingService bookingService) {

        this.bookingService = bookingService;
    }

    @PostMapping("/book-cab/{customerId}")
    public BookingResponse bookCab(@RequestBody BookingRequest bookingRequest , @PathVariable int customerId){
        return bookingService.bookCab(bookingRequest , customerId);
    }
}
