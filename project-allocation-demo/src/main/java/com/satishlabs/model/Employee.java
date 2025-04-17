package com.satishlabs.model;

import java.time.LocalDate;
import java.util.List;
import com.satishlabs.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "employees")
public class Employee {
	@Id
	private String objectId;

    private String employeeId;
    private String employeeName;
    private CapabilityCentre capabilityCentre;
    private LocalDate dateOfJoining;
    private Designation designation;
    private String primarySkill;
    private String secondarySkill;
    private int overallExperience;
    private List<ProjectAllocation> allocations;
    
    public Employee() {}
    
	public Employee(String employeeId, String employeeName, CapabilityCentre capabilityCentre, LocalDate dateOfJoining,
			Designation designation, String primarySkill, String secondarySkill, int overallExperience,
			List<ProjectAllocation> allocations) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.capabilityCentre = capabilityCentre;
		this.dateOfJoining = dateOfJoining;
		this.designation = designation;
		this.primarySkill = primarySkill;
		this.secondarySkill = secondarySkill;
		this.overallExperience = overallExperience;
		this.allocations = allocations;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public CapabilityCentre getCapabilityCentre() {
		return capabilityCentre;
	}

	public void setCapabilityCentre(CapabilityCentre capabilityCentre) {
		this.capabilityCentre = capabilityCentre;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public String getPrimarySkill() {
		return primarySkill;
	}

	public void setPrimarySkill(String primarySkill) {
		this.primarySkill = primarySkill;
	}

	public String getSecondarySkill() {
		return secondarySkill;
	}

	public void setSecondarySkill(String secondarySkill) {
		this.secondarySkill = secondarySkill;
	}

	public int getOverallExperience() {
		return overallExperience;
	}

	public void setOverallExperience(int overallExperience) {
		this.overallExperience = overallExperience;
	}

	public List<ProjectAllocation> getAllocations() {
		return allocations;
	}

	public void setAllocations(List<ProjectAllocation> allocations) {
		this.allocations = allocations;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", capabilityCentre="
				+ capabilityCentre + ", dateOfJoining=" + dateOfJoining + ", designation=" + designation
				+ ", primarySkill=" + primarySkill + ", secondarySkill=" + secondarySkill + ", overallExperience="
				+ overallExperience + ", allocations=" + allocations + "]";
	}
    
    
}

