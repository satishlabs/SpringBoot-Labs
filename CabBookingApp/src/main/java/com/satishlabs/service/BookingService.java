package com.satishlabs.service;

import java.util.List;

import com.satishlabs.entity.Booking;

public interface BookingService {
	Booking createBooking(Booking booking);
	Booking getBooking(Long id);
	List<Booking> getBookingsByUserId(Long userId);
}
