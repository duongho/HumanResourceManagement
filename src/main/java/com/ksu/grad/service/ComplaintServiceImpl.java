package com.ksu.grad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksu.grad.dao.ComplaintDAO;
import com.ksu.grad.entity.EmployeeHistory;

@Service
public class ComplaintServiceImpl implements ComplaintService {

	@Autowired
	ComplaintDAO dao;

	@Override
	public List<EmployeeHistory> getAllComplaintsForEmployee(int empId) {
		return dao.getAllComplaintsForEmployee(empId);
	}

	@Override
	public List<EmployeeHistory> getAllComplaints() {
		return dao.getAllComplaints();
	}

	@Override
	public List<EmployeeHistory> getAllComplaintsUnderManager(int managerId) {
		return dao.getAllComplaintsUnderManager(managerId);
	}
	
	
}
