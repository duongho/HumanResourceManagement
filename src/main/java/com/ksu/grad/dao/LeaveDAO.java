package com.ksu.grad.dao;

import java.util.Date;
import java.util.List;

import com.ksu.grad.entity.Attribute;
import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.entity.Status;
import com.ksu.grad.pojo.LeavePOJO;

public interface LeaveDAO {
	
	
	List<EmployeeHistory> getAllLeaveRequest(int empId);
	
	List<EmployeeHistory> displayPendingLeaveRequest();
	
	
	List<EmployeeHistory> getAllPendingEmpRequestForManager(int managerId);
	
	Date createLeave(LeavePOJO leave);
	
	Date approveLeave(LeavePOJO leave);
	
	Date denyLeave (LeavePOJO leave);
}
