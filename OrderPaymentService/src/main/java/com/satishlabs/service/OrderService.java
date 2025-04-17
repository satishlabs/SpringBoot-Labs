package com.satishlabs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satishlabs.dto.OrderRequest;
import com.satishlabs.model.Order;
import com.satishlabs.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	public Order placeOrder(OrderRequest request, double totalAmount) {
		Order order = new Order();
		order.setProductName(request.getProductName());
		order.setQuantity(request.getQuantity());
		order.setTotalAmount(totalAmount);
		return orderRepository.save(order);
	}

	public Order getOrderById(Long orderId) {
		return orderRepository.findOrderById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
	}
}
