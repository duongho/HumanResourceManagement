package com.ksu.grad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ksu.grad.entity.Employee;
import com.ksu.grad.service.EmployeeService;

@Controller
@RequestMapping("api/employee")
public class EmployeeController {

	@Autowired
	EmployeeService empService;
	
	@GetMapping("all")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> list = empService.getAllEmployees();
		
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}
	
	
	@GetMapping("{empId}")
	public ResponseEntity<Employee> getEmployeeProfile(@PathVariable("empId") int empId) {
		Employee empProfile = empService.getEmployeeById(empId);
		
		return new ResponseEntity<Employee>(empProfile, HttpStatus.OK);
	}
}
