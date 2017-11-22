package com.ksu.grad.service;

import java.util.List;

import com.ksu.grad.entity.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(int empId);
}
