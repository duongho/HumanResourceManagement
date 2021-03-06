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

import com.ksu.grad.authentication.SecurityConstants;
import com.ksu.grad.entity.Employee;
import com.ksu.grad.pojo.EmployeePOJO;
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

    @RequestMapping(value="/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Employee> register(@RequestBody EmployeePOJO employeeModel) throws Exception{
    	Employee newEmployee = empService.registerEmployee(employeeModel);    
    	return new ResponseEntity<Employee>(newEmployee, HttpStatus.OK);
    }	
    
    //TODO: do the actual implementation...
    @RequestMapping(value="/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Employee> updateProfile(@RequestBody EmployeePOJO newEmployee){   
    	
    	//only address, phone number, and email are updatable
    	Employee updatedEmp = empService.updateEmployee(newEmployee);
    	
    	if (updatedEmp == null ){
        	return new ResponseEntity<Employee>(updatedEmp, HttpStatus.BAD_REQUEST);
    	}
    	
    	
    	
    	return new ResponseEntity<Employee>(updatedEmp, HttpStatus.OK);
    		
    }
    
    
    @RequestMapping(value="/quit/{empId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String>  terminateEmployee(@PathVariable int empId){
    	boolean status = empService.quitEmployee(empId);
    	if (status==true) {
    		return new ResponseEntity<String>("Quit", HttpStatus.OK);
    	}else {    	
    		return new ResponseEntity<String>("Not Quit", HttpStatus.OK);
    	}
    }
    
        
    @RequestMapping(value="/{username}/password/recovery", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String>  recoverPassword(@PathVariable("username") String username){
    	
		String password = empService.recoverPassword(username);
		
        String jsonString = "{\"password\":" + "\"" +password + "\"}";
		
		return new ResponseEntity<String>(jsonString, HttpStatus.OK);
    }
    
    
    @RequestMapping(value="/find/{firstname}/{lastname}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Employee>  findEmployeebyFirstandLastname(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname){
		Employee emp = empService.getEmployeeByFirstandLastName(firstname, lastname);
		if(emp==null) {
			return new ResponseEntity<Employee>(emp, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);    	
    }
    @RequestMapping(value="/find/{username}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Employee>  findEmployeebyUsername(@PathVariable("username") String username){
		Employee emp = empService.getEmployeeByUsername(username);
		if(emp==null) {
			return new ResponseEntity<Employee>(emp, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);    	
    }
    
    
 
}
