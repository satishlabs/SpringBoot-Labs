package com.satishlabs.service;

import com.satishlabs.dto.ProjectAllocationDTO;
import com.satishlabs.model.Employee;
import com.satishlabs.model.ProjectAllocation;
import com.satishlabs.repository.EmployeeRepository;
import com.satishlabs.repository.ProjectAllocationRepository;
import com.satishlabs.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Service
public class ProjectAllocationService {
    private static final Logger log =  LoggerFactory.getLogger(ProjectAllocationService.class);

    @Autowired
    private ProjectAllocationRepository projectAllocationRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmailService emailService;

    public Mono<ProjectAllocation> allocateProject(ProjectAllocationDTO allocationDTO) {
        ProjectAllocation allocation = new ProjectAllocation();
        allocation.setEmployeeId(allocationDTO.getEmployeeId());
        allocation.setProjectId(allocationDTO.getProjectId());
        allocation.setAllocation(allocationDTO.getAllocation());
        allocation.setAllocationStartDate(allocationDTO.getAllocationStartDate());
        allocation.setAllocationEndDate(allocationDTO.getAllocationEndDate());

        return projectAllocationRepository.save(allocation)
                .doOnSuccess(a -> emailService.sendAllocationNotification("Employee allocated to project: " + a.getProjectId()));
    }

    public Mono<ProjectAllocation> modifyAllocation(ProjectAllocationDTO allocationDTO) {
        return projectAllocationRepository.findById(allocationDTO.getAllocationId()) // FIX: Using allocationId
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Allocation not found")))
                .flatMap(existing -> {
                    existing.setProjectId(allocationDTO.getProjectId());
                    existing.setAllocation(allocationDTO.getAllocation());
                    existing.setAllocationStartDate(allocationDTO.getAllocationStartDate());
                    existing.setAllocationEndDate(allocationDTO.getAllocationEndDate());
                    return projectAllocationRepository.save(existing);
                });
    }


    public Mono<Employee> getSecondMostExperiencedEmployee(String projectId) {
        return projectAllocationRepository.findByProjectId(projectId)
                .flatMap(allocation -> employeeRepository.findByEmployeeId(allocation.getEmployeeId()))
                .sort(Comparator.comparing(Employee::getOverallExperience).reversed())
                .skip(1)
                .next()
                .doOnNext(emp -> log.info("👤 Second most experienced employee: {}", emp));
    }


    public Flux<Employee> findEmployeesBySkills(String primarySkill, String secondarySkill) {
        log.info("🔍 Querying MongoDB for primarySkill={} and secondarySkill={}", primarySkill, secondarySkill);
        return employeeRepository.findByPrimarySkillAndSecondarySkill(primarySkill, secondarySkill)
                .doOnNext(emp -> log.info("✅ Found employee: {}", emp));
    }

    public Flux<Employee> getUnallocatedEmployeesBySkill(String primarySkill) {
        return employeeRepository.findByPrimarySkillNot(primarySkill)
                .switchIfEmpty(Flux.defer(() -> {
                    log.warn("❌ No employees found excluding skill: {}", primarySkill);
                    return Flux.empty();
                }));
    }

    public Flux<ProjectAllocation> getAllocationsForProject(String projectId) {
        log.info("🚀 Fetching allocations for project: {}", projectId);
        return projectAllocationRepository.findByProjectId(projectId)
                .doOnNext(allocation -> log.info("🔍 Found allocation: {}", allocation))
                .switchIfEmpty(Mono.error(new RuntimeException("❌ No allocations found for project: " + projectId)));
    }

    public Flux<Employee> getEmployeesForProject(String projectId) {
        return getAllocationsForProject(projectId)
                .flatMap(allocation -> {
                    log.info("🔍 Looking for Employee with ID: {}", allocation.getEmployeeId());

                    return employeeRepository.findByEmployeeId(allocation.getEmployeeId())
                            .doOnNext(emp -> log.info("✅ Found Employee: {}", emp))
                            .switchIfEmpty(Mono.defer(() -> {
                                log.error("❌ No Employee found for ID: {}", allocation.getEmployeeId());
                                return Mono.empty();
                            }));
                })
                .doOnComplete(() -> log.info("🚀 Employee fetch process completed."));
    }


}
