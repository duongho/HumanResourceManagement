package com.ksu.grad.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.pojo.ComplaintPOJO;
import com.ksu.grad.service.ComplaintService;
import com.ksu.grad.service.EmployeeService;

@Controller
@RequestMapping("api/humanresource")
public class HumanResourceController {
	
	@Autowired
	public EmployeeService empService;
	
	@Autowired
	ComplaintService complaintService;
	
	
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
	
	@GetMapping("all")
	public ResponseEntity<List<EmployeeHistory>> getAllComplaints(){		
		List<EmployeeHistory> complaintHistories = complaintService.getAllComplaints();		
		return new ResponseEntity<List<EmployeeHistory>>(complaintHistories, HttpStatus.OK);
	}
	/**
	 * HR escalate complaints to manager
	 * @return
	 */
	@RequestMapping(value="/assigncomplaint", method=RequestMethod.POST)
	public ResponseEntity<String> escalateComplaint(@RequestBody ComplaintPOJO complaint){		
		Date validFrom = complaintService.escalateComplaint(complaint);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");		
		return new ResponseEntity<String>(sdf.format(validFrom), HttpStatus.OK);
		
	} 
	
	

}
