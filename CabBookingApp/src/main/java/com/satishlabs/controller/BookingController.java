package com.satishlabs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satishlabs.entity.Booking;
import com.satishlabs.service.BookingService;

@RestController
@RequestMapping("/api/bookins")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping
	public ResponseEntity<Booking> bookTaxi(@RequestBody Booking booking) {
		return new ResponseEntity<>(bookingService.createBooking(booking), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Booking> getBooking(@PathVariable Long id) {
		return ResponseEntity.ok(bookingService.getBooking(id));
	}
	
	@GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getBookingsByUserId(userId));
    }

}
