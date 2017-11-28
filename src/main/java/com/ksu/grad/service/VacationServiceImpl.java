package com.ksu.grad.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksu.grad.dao.VacationDAO;
import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.pojo.VacationPOJO;

@Service
public class VacationServiceImpl implements VacationService {

	@Autowired
	VacationDAO dao;
	
	@Override
	public Date approveVacation(VacationPOJO vacation) {
		return dao.approveVacation(vacation);
	}

	@Override
	public Date denyVacation(VacationPOJO vacation) {
		return dao.denyVacation(vacation);

	}

	@Override
	public Date requestVacation(VacationPOJO vacation) {
		return dao.requestVacation(vacation);

	}

	@Override
	public List<EmployeeHistory> getAllPendingVacationRequestForEmployee(int empId) {
		return dao.getAllPendingVacationRequestForEmployee(empId);
	}

	@Override
	public List<EmployeeHistory> getAllPendingVacationRequestForManager(int managerId) {
		return dao.getAllPendingVacationRequestForManager(managerId);
	}

}
