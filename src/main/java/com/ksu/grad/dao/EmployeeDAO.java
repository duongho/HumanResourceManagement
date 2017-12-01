package com.ksu.grad.dao;

import java.util.List;

import com.ksu.grad.entity.Employee;
import com.ksu.grad.entity.State;

public interface EmployeeDAO {

	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(int empId);
	
	Employee registerEmplyee(Employee employee);
	
	Employee updateEmployee(Employee employee);
		
	boolean  quitEmployee(int empId);
	
	List<Employee> getAllManagers();	
	
	boolean updatePassword(String username, String encryptPassword);
	
	Employee getEmployeeByUserName (String userName);
	
	State getStateByName (String name);
	
}
