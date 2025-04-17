package com.satishlabs.controller;


import com.satishlabs.model.Employee;
import com.satishlabs.model.ProjectAllocation;
import com.satishlabs.service.ProjectAllocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/project-allocations")
public class ProjectAllocationController {
    private static final Logger logger = LoggerFactory.getLogger(ProjectAllocationController.class);

    @Autowired
    private ProjectAllocationService projectAllocationService;

    @PostMapping("/add")
    //@PreAuthorize("hasRole('ADMIN')")
    public Mono<ProjectAllocation> addProjectAllocation(@RequestBody ProjectAllocation allocation) {
        return projectAllocationService.addProjectAllocation(allocation);
    }

    @GetMapping("/employees-by-skill")
    public Flux<Employee> fetchEmployeesBySkills(@RequestParam String primarySkill, @RequestParam String secondarySkill) {
        logger.info("Fetching employees with primary skill: {} and secondary skill: {}", primarySkill, secondarySkill);
        return projectAllocationService.fetchEmployeesBySkills(primarySkill, secondarySkill);
    }


    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<ProjectAllocation>> updateProjectAllocation(
            @PathVariable String id,
            @RequestBody ProjectAllocation allocation) {

        return projectAllocationService.updateProjectAllocation(id, allocation)
                .map(updated -> ResponseEntity.ok(updated))
                .onErrorResume(e -> {
                    logger.error("Error updating allocation: {}", e.getMessage());
                    return Mono.just(ResponseEntity.badRequest().build());
                });
    }

    @GetMapping("/second-most-experienced/{projectId}")
    public Mono<Employee> getSecondMostExperiencedEmployee(@PathVariable String projectId) {
        return projectAllocationService.getSecondMostExperiencedEmployee(projectId);
    }

    @GetMapping("/employees-by-skill")
    public Flux<Employee> fetchEmployeesBySkill(@RequestParam String primarySkill, @RequestParam String secondarySkill) {
        return projectAllocationService.fetchEmployeesBySkill(primarySkill, secondarySkill);
    }

    @GetMapping("/employees-not-allocated")
    public Flux<Employee> fetchEmployeesNotAllocatedWithSkill(@RequestParam String primarySkill) {
        return projectAllocationService.fetchEmployeesNotAllocatedWithSkill(primarySkill);
    }

}
