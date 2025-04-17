package com.satishlabs.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satishlabs.entity.Order;
import com.satishlabs.entity.User;
import com.satishlabs.repository.OrderRepository;
import com.satishlabs.repository.UserRepository;

@Service
public class DataService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public DataService(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public List<Map<String, Object>> getCombinedData() {
        List<User> users = userRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (User user : users) {
            List<Order> orders = orderRepository.findByUserId(user.getId().toString());
            Map<String, Object> userData = new HashMap<>();
            userData.put("user", user);
            userData.put("orders", orders);
            result.add(userData);
        }
        return result;
    }
}
