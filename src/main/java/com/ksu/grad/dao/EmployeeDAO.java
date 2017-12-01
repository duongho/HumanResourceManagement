package com.ksu.grad.dao;

import java.util.List;

import com.ksu.grad.entity.Employee;

public interface EmployeeDAO {

	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(int empId);
	
	Employee registerEmplyee(Employee employee);
	
	Employee updateEmployee(Employee employee);
		
	boolean  quitEmployee(int empId);
	
	List<Employee> getAllManagers();	
	
	boolean updatePassword(String username, String encryptPassword);
	
	Employee getEmployeeByFirstandLastName(String firstName, String lastName);

}
