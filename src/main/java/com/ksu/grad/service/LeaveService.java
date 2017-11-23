package com.ksu.grad.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.entity.Leaves;

public interface LeaveService {
	
	EmployeeHistory leaverequest(String offFromDate, 
    		String offToDate, int offType,String justification, int managerId,int empId) throws ParseException;

	List<EmployeeHistory> displayLeaveRequest(int empId);
	
	List<EmployeeHistory> pendingDisplayLeaveRequest();
	
	String approveLeaveRequest(int empId, String status);
	
	List<EmployeeHistory> getAllPendingEmpRequestForManager(int managerId);
}
