package com.satishlabs.repository;

import com.satishlabs.model.Employee;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {

    Flux<Employee> findByPrimarySkillAndSecondarySkill(String primarySkill, String secondarySkill);

    @Query("{ 'employeeId': ?0 }")
    Mono<Employee> findByEmployeeId(String employeeId);

    @Query("{ 'primarySkill': { $not: { $regex: ?0, $options: 'i' } } }")
    Flux<Employee> findByPrimarySkillNot(String primarySkill);


    @Query("{ 'employeeId': { $nin: ?0 }, 'primarySkill': ?1 }")
    Flux<Employee> findUnallocatedEmployeesByPrimarySkill(List<String> allocatedEmployeeIds, String primarySkill);

}
