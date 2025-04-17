package com.satishlabs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.satishlabs.dao.CustomerDAO;
import com.satishlabs.pojo.Customer;

@SpringBootApplication
public class SpringMongoLabApplication implements CommandLineRunner{
	
	@Autowired
	private CustomerDAO custDAO;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringMongoLabApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		/*
		 * Customer cust = new Customer(106, "test4", "test4@gmail", 5555, "Bangalore",
		 * 13000); custDAO.save(cust);
		 */
		
		List<Customer> mylist = custDAO.findAll();
		mylist.forEach(System.out::println);
		
		System.out.println("Customer count: "+mylist.size());
	}
	
}
