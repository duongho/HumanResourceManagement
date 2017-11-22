package com.ksu.grad.dao;


import java.util.List;

import com.ksu.grad.entity.EmployeeHistory;

public interface ComplaintDAO {
	
	public List<EmployeeHistory> getAllComplaintsForEmployee(int empId);
	public List<EmployeeHistory> getAllComplaints();
}
