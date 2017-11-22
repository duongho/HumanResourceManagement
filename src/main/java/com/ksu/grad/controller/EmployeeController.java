package com.ksu.grad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ksu.grad.entity.Employee;
import com.ksu.grad.entity.Person;
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

    @RequestMapping(value="/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Employee> register(@RequestBody String firstname, String lastname, String address, 
    		String email, String phone,String salary, String startDate,String username, String city, String state, String zipcode) throws Exception{
    	Employee newEmployee = empService.registerEmployee(firstname, lastname, address, email, phone, salary, startDate, username,city,state,zipcode);    
    	return new ResponseEntity<Employee>(newEmployee, HttpStatus.OK);
    }	
    
    @RequestMapping(value="/updateprofile/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Employee> updateProfile(@RequestBody String firstname, String lastname, String address, 
    		String email, String phone,String salary, String startDate,String username, String city, String state, 
    		String zipcode,String password, @PathVariable int id) throws Exception{
     	Employee newEmployee = empService.updateProfile(firstname, lastname, address, email, phone, salary, startDate, username, city, state, zipcode, password, id);    
    	if(newEmployee!=null && newEmployee.getId()== id) {
    		return new ResponseEntity<Employee>(newEmployee, HttpStatus.OK);
    	}
    	else {
    		return new ResponseEntity<Employee>(newEmployee, HttpStatus.BAD_REQUEST);
    	}    	
    		
    }
     	
    @RequestMapping(value="/getprofile/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Employee> getProfile(@PathVariable int id){
    	Employee employee = empService.getEmployeebyId(id);
    	return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }
   
}
