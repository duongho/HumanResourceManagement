package com.ksu.grad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ksu.grad.entity.Employee;
import com.ksu.grad.service.EmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {

	@Autowired
	EmployeeService empService;
	
	@GetMapping("all")
	public ResponseEntity<List<Employee>> getAllPerson() {
		List<Employee> list = empService.getAllEmployees();
		
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}
}
