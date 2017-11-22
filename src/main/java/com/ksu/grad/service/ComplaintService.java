package com.ksu.grad.service;

import java.util.List;

import com.ksu.grad.entity.Complaint;
import com.ksu.grad.entity.EmployeeHistory;

public interface ComplaintService {
		
	List<EmployeeHistory> getAllComplaintsForEmployee(int empId);
	List<EmployeeHistory> getAllComplaints();
}
