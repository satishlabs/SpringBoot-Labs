package com.satishlabs.repository;

import com.satishlabs.model.Employee;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;


public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {

    Flux<Employee> findByPrimarySkillAndSecondarySkill(String primarySkill, String secondarySkill);
    Flux<Employee> findByPrimarySkillNot(String primarySkill);
}
