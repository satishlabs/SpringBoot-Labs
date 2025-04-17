package com.satishlabs.service;

import org.springframework.stereotype.Service;

@Service
public class BookService {
	public void getBookById(int id) {
		System.out.println("Fetching book with ID: "+id);
	}
	
	public void throwError() {
		throw new RuntimeException("Simulted Exception");
	}
}
