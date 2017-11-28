package com.ksu.grad.service;

import java.util.Date;
import java.util.List;

import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.pojo.VacationPOJO;

public interface VacationService {

	Date approveVacation(VacationPOJO vacation);
	
	Date denyVacation(VacationPOJO vacation);
	
	Date requestVacation(VacationPOJO vacation);
	
	List<EmployeeHistory> getAllPendingVacationRequestForEmployee (int empId);
	
	List<EmployeeHistory> getAllPendingVacationRequestForManager (int managerId);

}
