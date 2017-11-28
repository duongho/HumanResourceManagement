package com.ksu.grad.dao;

import java.util.Date;
import java.util.List;

import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.pojo.VacationPOJO;

public interface VacationDAO {

	Date requestVacation (VacationPOJO vacation);
	
	Date denyVacation (VacationPOJO vacation);
	
	Date approveVacation (VacationPOJO vacation);
	
	List<EmployeeHistory> getAllPendingVacationRequestForManager (int managerId);
	
	List<EmployeeHistory> getAllPendingVacationRequestForEmployee (int empId);
}
