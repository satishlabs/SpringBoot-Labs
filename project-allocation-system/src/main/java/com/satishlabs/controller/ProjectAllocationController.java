package com.satishlabs.controller;

import com.satishlabs.dto.ProjectAllocationDTO;
import com.satishlabs.model.Employee;
import com.satishlabs.model.ProjectAllocation;
import com.satishlabs.service.ProjectAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/allocations")
public class ProjectAllocationController {

    @Autowired
    private ProjectAllocationService projectAllocationService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Mono<ProjectAllocation> allocate(@RequestBody ProjectAllocationDTO allocationDTO) {
        return projectAllocationService.allocateProject(allocationDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public Mono<ProjectAllocation> modify(@RequestBody ProjectAllocationDTO allocationDTO) {
        return projectAllocationService.modifyAllocation(allocationDTO);
    }

    @GetMapping("/second-experienced/{projectId}")
    public Mono<Employee> getSecondMostExperienced(@PathVariable String projectId) {
        return projectAllocationService.getSecondMostExperiencedEmployee(projectId);
    }

    @GetMapping("/employees-by-skills")
    public Flux<Employee> getEmployeesBySkills(@RequestParam String primarySkill, @RequestParam String secondarySkill) {
        return projectAllocationService.findEmployeesBySkills(primarySkill, secondarySkill);
    }

    @GetMapping("/unallocated/{primarySkill}")
    public Flux<Employee> getUnallocatedEmployees(@PathVariable String primarySkill) {
        return projectAllocationService.getUnallocatedEmployeesBySkill(primarySkill);
    }

    @GetMapping("/allocation/{projectId}")
    public Flux<ProjectAllocation> getAllocations(@PathVariable String projectId) {
        return projectAllocationService.getAllocationsForProject(projectId);
    }

    @GetMapping("/employees/{projectId}")
    public Flux<Employee> getEmployees(@PathVariable String projectId) {
        return projectAllocationService.getEmployeesForProject(projectId);
    }

}
