package com.ksu.grad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ksu.grad.entity.EmployeeHistory;
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
}
