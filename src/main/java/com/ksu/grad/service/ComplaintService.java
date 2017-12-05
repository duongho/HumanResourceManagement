package com.ksu.grad.service;

import java.util.Date;
import java.util.List;

import com.ksu.grad.entity.Complaint;
import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.pojo.ComplaintPOJO;
import com.ksu.grad.pojo.ReviewPOJO;

public interface ComplaintService {
		
	List<EmployeeHistory> getAllComplaintsForEmployee(int empId);
	List<EmployeeHistory> getAllComplaints();	
	List<EmployeeHistory> getAllComplaintsUnderManager(int managerId);
	Date fileComplaint(ComplaintPOJO complaintPojo);
	Date responseComplaint(ComplaintPOJO complaint);
	Date escalateComplaint(ComplaintPOJO complaintPojo);
	
}
