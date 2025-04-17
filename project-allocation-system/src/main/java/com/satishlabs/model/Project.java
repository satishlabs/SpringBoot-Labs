package com.satishlabs.model;

import com.satishlabs.util.AccountName;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document(collection = "projects")
public class Project {
    @Id
    private String id;  // This will store MongoDB's _id (ObjectId)

    @Field("projectId")  // Explicitly map this field to MongoDB's projectId
    private String projectId;
    private AccountName accountName;
    private String projectName;
    private float allocation;
    private LocalDate projectStartDate;
    private LocalDate projectEndDate;
    private String remarks;

    public Project(){}

    public Project(String id, String projectId, AccountName accountName, String projectName, float allocation, LocalDate projectStartDate, LocalDate projectEndDate, String remarks) {
        this.id = id;
        this.projectId = projectId;
        this.accountName = accountName;
        this.projectName = projectName;
        this.allocation = allocation;
        this.projectStartDate = projectStartDate;
        this.projectEndDate = projectEndDate;
        this.remarks = remarks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public AccountName getAccountName() {
        return accountName;
    }

    public void setAccountName(AccountName accountName) {
        this.accountName = accountName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public double getAllocation() {
        return allocation;
    }

    public void setAllocation(float allocation) {
        this.allocation = allocation;
    }

    public LocalDate getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(LocalDate projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public LocalDate getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(LocalDate projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", projectId='" + projectId + '\'' +
                ", accountName=" + accountName +
                ", projectName='" + projectName + '\'' +
                ", allocation=" + allocation +
                ", projectStartDate=" + projectStartDate +
                ", projectEndDate=" + projectEndDate +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
