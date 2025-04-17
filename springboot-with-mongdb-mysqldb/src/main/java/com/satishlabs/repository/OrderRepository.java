package com.satishlabs.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.satishlabs.entity.Order;

public interface OrderRepository extends MongoRepository<Order, String>{
	List<Order> findByUserId(String userId);
}
