package com.satishlabs.service;

import com.satishlabs.model.Employee;
import com.satishlabs.model.ProjectAllocation;
import com.satishlabs.repository.EmployeeRepository;
import com.satishlabs.repository.ProjectAllocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class ProjectAllocationService {
	    private static final Logger logger = LoggerFactory.getLogger(ProjectAllocationService.class);
	    
	    @Autowired
	    private ProjectAllocationRepository projectAllocationRepository;

		@Autowired
		private EmployeeRepository employeeRepository;


	public Mono<ProjectAllocation> addProjectAllocation(ProjectAllocation allocation) {
		return projectAllocationRepository.save(allocation);
	}

	public Flux<Employee> fetchEmployeesBySkills(String primarySkill, String secondarySkill) {
		logger.info("Fetching employees with primary skill: {} and secondary skill: {}", primarySkill, secondarySkill);
		return employeeRepository.findByPrimarySkillAndSecondarySkill(primarySkill, secondarySkill);
	}

	public Mono<ProjectAllocation> updateProjectAllocation(String id, ProjectAllocation allocation) {
		logger.info("Updating project allocation with ID: {}", id);

		return projectAllocationRepository.findById(id)
				.doOnNext(existing -> logger.info("Existing allocation found: {}", existing))
				.flatMap(existing -> {
					if (allocation.getProjectName() != null) {
						existing.setProjectName(allocation.getProjectName());
					}
					if (allocation.getAllocation() > 0.1 && allocation.getAllocation() <= 1.0) {
						existing.setAllocation(allocation.getAllocation());
					} else {
						return Mono.error(new IllegalArgumentException("Allocation must be between 0.1 and 1.0"));
					}
					if (allocation.getProjectStartDate() != null) {
						existing.setProjectStartDate(allocation.getProjectStartDate());
					}
					if (allocation.getProjectEndDate() != null) {
						existing.setProjectEndDate(allocation.getProjectEndDate());
					}
					if (allocation.getRemarks() != null) {
						existing.setRemarks(allocation.getRemarks());
					}
					if (allocation.getAccountName() != null) {
						existing.setAccountName(allocation.getAccountName());
					}

					return projectAllocationRepository.save(existing);
				})
				.doOnSuccess(updated -> logger.info("Updated allocation: {}", updated))
				.switchIfEmpty(Mono.error(new RuntimeException("No allocation found with ID: " + id)));
	}

	public Mono<Employee> getSecondMostExperiencedEmployee(String projectId) {
		return employeeRepository.findAll()
				.sort((e1, e2) -> Integer.compare(e2.getOverallExperience(), e1.getOverallExperience()))
				.elementAt(1);
	}

	@Cacheable("employeesBySkill")
	public Flux<Employee> fetchEmployeesBySkill(String primarySkill, String secondarySkill) {
		return employeeRepository.findByPrimarySkillAndSecondarySkill(primarySkill, secondarySkill);
	}

	@Cacheable("employeesNotAllocated")
	public Flux<Employee> fetchEmployeesNotAllocatedWithSkill(String primarySkill) {
		return employeeRepository.findByPrimarySkillNot(primarySkill);
	}

}

