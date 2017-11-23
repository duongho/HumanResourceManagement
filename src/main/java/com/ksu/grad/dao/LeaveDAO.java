package com.ksu.grad.dao;

import java.util.List;

import com.ksu.grad.entity.Attribute;
import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.entity.Leaves;
import com.ksu.grad.entity.Status;

public interface LeaveDAO {
	
	boolean leaverequest(EmployeeHistory emplHistory);
	
	List<EmployeeHistory> getAllLeaveRequest(int empId);
	
	List<EmployeeHistory> displayPendingLeaveRequest();
	
	boolean approveLeaveRequest();
	
	Attribute getAttributeforCreate();
	
	Status getStatusforCreate();
}
