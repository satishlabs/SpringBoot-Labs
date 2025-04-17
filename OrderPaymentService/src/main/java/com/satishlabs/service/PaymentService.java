package com.satishlabs.service;

import org.springframework.stereotype.Service;

import com.satishlabs.dto.PaymentRequest;

@Service
public class PaymentService {
	public double calculateAmount(PaymentRequest request) {
		return request.getQuantity()*request.getPricePerUnit();
	}
}
