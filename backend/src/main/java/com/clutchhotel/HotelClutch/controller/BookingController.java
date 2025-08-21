package com.clutchhotel.HotelClutch.controller;

import com.clutchhotel.HotelClutch.dto.Response;
import com.clutchhotel.HotelClutch.entity.Booking;
import com.clutchhotel.HotelClutch.service.interfac.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private IBookingService bookingService;

    @PostMapping("/book-room/{roomId}/{userId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Response> saveBooking(
            @PathVariable Long roomId,
            @PathVariable Long userId,
            @RequestBody Booking bookingRequest
    ){

        Response response = bookingService.saveBooking(userId, roomId, bookingRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllBookings(){
        Response response = bookingService.getAllBookings();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get-by-confirmation-code/{confirmationCode}")
    public ResponseEntity<Response> getBookingByConfirmationCode(@PathVariable String confirmationCode){
        Response response = bookingService.findBookingByConfirmationCode(confirmationCode);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/cancel/{bookingId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> cancelBooking(@PathVariable Long bookingId){
        Response response = bookingService.cancelBooking(bookingId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }




}
