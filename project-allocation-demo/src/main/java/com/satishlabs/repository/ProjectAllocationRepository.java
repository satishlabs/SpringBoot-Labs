package com.satishlabs.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.satishlabs.model.ProjectAllocation;

public interface ProjectAllocationRepository extends ReactiveMongoRepository<ProjectAllocation, String> {
	
}
