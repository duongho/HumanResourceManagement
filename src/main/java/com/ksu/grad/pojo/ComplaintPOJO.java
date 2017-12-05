package com.ksu.grad.pojo;

public class ComplaintPOJO {

	public String getJsonDetails() {
		return jsonDetails;
	}
	public void setJsonDetails(String jsonDetails) {
		this.jsonDetails = jsonDetails;
	}
	public String getEmployeeFirstName() {
		return employeeFirstName;
	}
	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}
	public String getEmployeeLastName() {
		return employeeLastName;
	}
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	public String getModifiedByFirstName() {
		return modifiedByFirstName;
	}
	public void setModifiedByFirstName(String modifiedByFirstName) {
		this.modifiedByFirstName = modifiedByFirstName;
	}
	public String getModifiedByLastName() {
		return modifiedByLastName;
	}
	public void setModifiedByLastName(String modifiedByLastName) {
		this.modifiedByLastName = modifiedByLastName;
	}
	private String jsonDetails;
    private String employeeFirstName;
    private String employeeLastName;
    private String modifiedByFirstName;
    private String modifiedByLastName;
}
