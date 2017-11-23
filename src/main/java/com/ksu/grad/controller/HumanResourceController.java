package com.ksu.grad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ksu.grad.entity.Employee;
import com.ksu.grad.service.EmployeeService;

@Controller
@RequestMapping("api/humanresource")
public class HumanResourceController {
	
	@Autowired
	public EmployeeService empService;
	
	
	@RequestMapping(value="get/manager/all", method = RequestMethod.GET)
    @ResponseBody
	public ResponseEntity<List<Employee>> getAllMAnagers() {		
		List managerLst = empService.getAllManagers();
		return new ResponseEntity<List<Employee>>(managerLst, HttpStatus.OK);
	}
	
	@RequestMapping(value="/terminateemployee/{empId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Employee>  terminateEmployeeByHR(@PathVariable int empId){
    	boolean status = empService.quitEmployee(empId);
    	Employee employee = null;
    	if (status==true) {
    		employee = empService.getEmployeeById(empId);
    	} 
    	return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

}
