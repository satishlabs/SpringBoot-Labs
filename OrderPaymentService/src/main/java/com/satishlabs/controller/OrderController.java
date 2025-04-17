package com.satishlabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satishlabs.dto.OrderRequest;
import com.satishlabs.dto.PaymentRequest;
import com.satishlabs.model.Order;
import com.satishlabs.service.OrderService;
import com.satishlabs.service.PaymentService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/place")
	public Order placeOrder(@RequestBody OrderRequest request) {
		double totalAmount = paymentService.calculateAmount(new PaymentRequest(100.0, request.getQuantity()));
		return orderService.placeOrder(request, totalAmount);
	}
	
	@GetMapping("/{orderId}")
	public Order getOrder(@PathVariable Long orderId) {
		return orderService.getOrderById(orderId);
	}
}
