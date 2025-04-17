package com.satishlabs.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satishlabs.entity.Booking;
import com.satishlabs.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public Booking createBooking(Booking booking) {
		booking.setBookingTime(LocalDateTime.now());
		return bookingRepository.save(booking);
	}

	@Override
	public Booking getBooking(Long id) {
		return bookingRepository.findById(id).orElseThrow(() ->new RuntimeException("Booking id not found"));
	}

	@Override
	public List<Booking> getBookingsByUserId(Long userId) {
		return bookingRepository.findByUserId(userId);
	}

}
