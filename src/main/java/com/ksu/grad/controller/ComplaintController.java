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

import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.pojo.ComplaintPOJO;
import com.ksu.grad.pojo.ReviewPOJO;
import com.ksu.grad.service.ComplaintService;

@Controller
@RequestMapping("api/complaint")
public class ComplaintController {

	
	@Autowired
	ComplaintService service;
	
	
	@GetMapping("{empId}/all")
	public ResponseEntity<List<EmployeeHistory>> getComplaintsForEmployee(@PathVariable("empId") int empId){
		
		List<EmployeeHistory> complaintHistories = service.getAllComplaintsForEmployee(empId);
		
		return new ResponseEntity<List<EmployeeHistory>>(complaintHistories, HttpStatus.OK);
	}
	
	@GetMapping("all")
	public ResponseEntity<List<EmployeeHistory>> getAllComplaints(){
		
		List<EmployeeHistory> complaintHistories = service.getAllComplaints();
		
		return new ResponseEntity<List<EmployeeHistory>>(complaintHistories, HttpStatus.OK);
	}
	
	@GetMapping("manager/{id}")
	public ResponseEntity<List<EmployeeHistory>> getAllNewComplaintsForManagerReview(@PathVariable("id") int managerId){
		
		List<EmployeeHistory> complaintHistories = service.getAllComplaintsUnderManager(managerId);
		
		return new ResponseEntity<List<EmployeeHistory>>(complaintHistories, HttpStatus.OK);
	}
	
	/**
	 * This method creates a complaint
	 * @return
	 */
	@RequestMapping(value="create", method=RequestMethod.POST)
	public ResponseEntity<String> createComplaint(@RequestBody ComplaintPOJO complaint){
		
		Date validFrom = service.fileComplaint(complaint);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		return new ResponseEntity<String>(sdf.format(validFrom), HttpStatus.OK);
		
	} 
	

	/**
	 * This method complete a new complaint
	 * @return
	 */
	@RequestMapping(value="response", method=RequestMethod.POST)
	public ResponseEntity<String> updateComplaint(@RequestBody ComplaintPOJO complaint){
		
		Date validFrom = service.responseComplaint(complaint);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		
		return new ResponseEntity<String>(sdf.format(validFrom), HttpStatus.OK);
		
	} 
	
}
