package com.ksu.grad.dao;

import java.util.List;

import com.ksu.grad.entity.Employee;

public interface EmployeeDAO {

	List<Employee> getAllEmployees();
	
	Employee registerEmplyee(Employee employee);
	
	Employee getEmployeeById(int id);
	
	Employee updateEmployee(Employee employee);
	
	List<Employee> getAllManagers();
	
}
