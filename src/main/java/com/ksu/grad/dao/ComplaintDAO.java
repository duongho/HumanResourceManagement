package com.ksu.grad.dao;


import java.util.Date;
import java.util.List;
import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.pojo.ComplaintPOJO;
import com.ksu.grad.pojo.ReviewPOJO;

public interface ComplaintDAO {
	
	public List<EmployeeHistory> getAllComplaintsForEmployee(int empId);
	public List<EmployeeHistory> getAllComplaints();	
	public List<EmployeeHistory> getAllComplaintsUnderManager(int managerId);	
	public Date fileComplaint(ComplaintPOJO complaintPojo);
	public Date responseComplaint(ComplaintPOJO complaintPojo);
	public Date escalateComplaint(ComplaintPOJO complaintPojo);
}
