package com.satishlabs.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.satishlabs.pojo.Customer;

public interface CustomerDAO extends MongoRepository<Customer, String>{

}
