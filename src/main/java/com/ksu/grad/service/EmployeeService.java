package com.ksu.grad.service;

import java.text.ParseException;
import java.util.List;

import com.ksu.grad.entity.Employee;
import com.ksu.grad.pojo.EmployeePOJO;

public interface EmployeeService {

	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(int empId);
	
	Employee registerEmployee(EmployeePOJO employeeModel) throws ParseException;
	
	Employee updateProfile(String firstname, String lastname, String address, 
    		String email, String phone,String salary, String startDate,String username, 
    		String city, String state, String zipcode,String password,int id) throws ParseException;
		
	List<Employee> getAllManagers();
	
	boolean  quitEmployee(int empId);
	
	String recoverPassword(String username);

	Employee getEmployeeByFirstandLastName(String firstName, String lastName);
	
	
}
