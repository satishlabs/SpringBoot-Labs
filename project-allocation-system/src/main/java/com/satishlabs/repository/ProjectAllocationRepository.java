package com.satishlabs.repository;

import com.satishlabs.model.ProjectAllocation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProjectAllocationRepository extends ReactiveMongoRepository<ProjectAllocation, String> {

    @Query("{ 'projectId': { $regex: ?0, $options: 'i' } }")  // Case-insensitive match
    Flux<ProjectAllocation> findByProjectId(String projectId);

}
