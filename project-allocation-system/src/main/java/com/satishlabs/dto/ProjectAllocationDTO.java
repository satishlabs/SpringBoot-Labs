package com.satishlabs.dto;

import java.time.LocalDate;

public class ProjectAllocationDTO {
    private String allocationId;
    private String employeeId;
    private String projectId;
    private float allocation;
    private LocalDate allocationStartDate;
    private LocalDate allocationEndDate;

    public ProjectAllocationDTO(){}

    public ProjectAllocationDTO(String allocationId, String employeeId, String projectId, float allocation, LocalDate allocationStartDate, LocalDate allocationEndDate) {
        this.allocationId  = allocationId;
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.allocation = allocation;
        this.allocationStartDate = allocationStartDate;
        this.allocationEndDate = allocationEndDate;
    }

    public String getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(String allocationId) {
        this.allocationId = allocationId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public float getAllocation() {
        return allocation;
    }

    public void setAllocation(float allocation) {
        this.allocation = allocation;
    }

    public LocalDate getAllocationStartDate() {
        return allocationStartDate;
    }

    public void setAllocationStartDate(LocalDate allocationStartDate) {
        this.allocationStartDate = allocationStartDate;
    }

    public LocalDate getAllocationEndDate() {
        return allocationEndDate;
    }

    public void setAllocationEndDate(LocalDate allocationEndDate) {
        this.allocationEndDate = allocationEndDate;
    }

    @Override
    public String toString() {
        return "ProjectAllocationDTO{" +
                "allocationId='" + allocationId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", allocation=" + allocation +
                ", allocationStartDate=" + allocationStartDate +
                ", allocationEndDate=" + allocationEndDate +
                '}';
    }
}
