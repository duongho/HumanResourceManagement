package com.ksu.grad.service;

import java.util.Date;
import java.util.List;

import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.pojo.LeavePOJO;

public interface LeaveService {
	
	List<EmployeeHistory> displayLeaveRequest(int empId);
	
	List<EmployeeHistory> pendingDisplayLeaveRequest();
		
	List<EmployeeHistory> getAllPendingEmpRequestForManager(int managerId);
	
	Date requestLeave(LeavePOJO leaveRequest);
	
	Date approveLeave(LeavePOJO leaveRequest);
	
	Date denyLeave (LeavePOJO leaveRequest);

}
