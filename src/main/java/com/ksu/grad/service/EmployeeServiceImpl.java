package com.ksu.grad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksu.grad.dao.EmployeeDAO;
import com.ksu.grad.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDAO employeeDAO;
	
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}


	@Override
	public Employee getEmployeeById(int empId) {
		return employeeDAO.getEmployeeById(empId);
	}
	

}
