package com.ksu.grad.dao;

import java.util.List;

import com.ksu.grad.entity.Employee;

public interface EmployeeDAO {

	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(int empId);
	
	Employee registerEmplyee(Employee employee);
	
	
	Employee updateEmployee(Employee employee);
	
	List<Employee> getAllManagers();	
}
