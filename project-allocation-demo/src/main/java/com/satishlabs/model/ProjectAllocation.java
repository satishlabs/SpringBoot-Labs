package com.satishlabs.model;

import java.time.LocalDate;
import com.satishlabs.util.AccountName;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "project_allocations")
public class ProjectAllocation {
    @Id
    private String id;
    private String employeeId;
    private AccountName accountName;
	private String projectName;
    private float allocation; // Range: 0.1 to 1
    private LocalDate projectStartDate;
    private LocalDate projectEndDate;
    private String remarks;

	public ProjectAllocation() {}

	public ProjectAllocation(String id, String employeeId, AccountName accountName, String projectName,
							 float allocation, LocalDate projectStartDate, LocalDate projectEndDate, String remarks) {
		super();
		this.id = id;
		this.employeeId = employeeId;
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

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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

	public float getAllocation() {
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
		return "ProjectAllocation [id=" + id + ", employeeId=" + employeeId + ", accountName=" + accountName
				+ ", projectName=" + projectName + ", allocation=" + allocation + ", projectStartDate="
				+ projectStartDate + ", projectEndDate=" + projectEndDate + ", remarks=" + remarks + "]";
	}
    
    
    
}
