package com.satishlabs.model;

import com.satishlabs.util.CapabilityCentre;
import com.satishlabs.util.Designation;
import jakarta.validation.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document(collection = "employees")
public class Employee {
    @Id
    private String id;  // This will store MongoDB's _id (ObjectId)

    @Field("employeeId")  // Explicitly map this field to MongoDB's employeeId
    private String employeeId;
    private String employeeName;
    private CapabilityCentre capabilityCentre;
    private LocalDate dateOfJoining;
    private Designation designation;
    @Field("primarySkill")
    private String primarySkill;
    private String secondarySkill;
    private int overallExperience;
    @Email
    private String email;

    public Employee() {
    }

    public Employee(String id, String employeeId, String employeeName, CapabilityCentre capabilityCentre, LocalDate dateOfJoining, Designation designation, String primarySkill, String secondarySkill, int overallExperience, String email) {
        this.id = id;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.capabilityCentre = capabilityCentre;
        this.dateOfJoining = dateOfJoining;
        this.designation = designation;
        this.primarySkill = primarySkill;
        this.secondarySkill = secondarySkill;
        this.overallExperience = overallExperience;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", capabilityCentre=" + capabilityCentre +
                ", dateOfJoining=" + dateOfJoining +
                ", designation=" + designation +
                ", primarySkill='" + primarySkill + '\'' +
                ", secondarySkill='" + secondarySkill + '\'' +
                ", overallExperience=" + overallExperience +
                ", email='" + email + '\'' +
                '}';
    }
}
