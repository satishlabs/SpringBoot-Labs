package com.satishlabs.dto;

import com.satishlabs.util.CapabilityCentre;
import com.satishlabs.util.Designation;

import java.time.LocalDate;

public class EmployeeDTO {
    private String employeeName;
    private CapabilityCentre capabilityCentre;
    private LocalDate dateOfJoining;
    private Designation designation;
    private String primarySkill;
    private String secondarySkill;
    private int overallExperience;

    public EmployeeDTO(){}

    public EmployeeDTO(String employeeName, CapabilityCentre capabilityCentre, LocalDate dateOfJoining, Designation designation, String primarySkill, String secondarySkill, int overallExperience) {
        this.employeeName = employeeName;
        this.capabilityCentre = capabilityCentre;
        this.dateOfJoining = dateOfJoining;
        this.designation = designation;
        this.primarySkill = primarySkill;
        this.secondarySkill = secondarySkill;
        this.overallExperience = overallExperience;
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

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employeeName='" + employeeName + '\'' +
                ", capabilityCentre=" + capabilityCentre +
                ", dateOfJoining=" + dateOfJoining +
                ", designation=" + designation +
                ", primarySkill='" + primarySkill + '\'' +
                ", secondarySkill='" + secondarySkill + '\'' +
                ", overallExperience=" + overallExperience +
                '}';
    }
}
