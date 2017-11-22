package com.ksu.grad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ksu.grad.entity.Complaint;
import com.ksu.grad.service.ComplaintService;

@Controller
@RequestMapping("complaint")
public class ComplaintController {

	
	@Autowired
	ComplaintService service;
	
	
	@GetMapping("{complaintId}")
	public ResponseEntity<Complaint> getComplaintById(@PathVariable("complaintId") int complaintId){
		Complaint complaint = service.getComplaintById(complaintId);
		
		return new ResponseEntity<Complaint>(complaint, HttpStatus.OK);
	}
	
	@GetMapping("{empId}/all")
	public ResponseEntity<List<Complaint>> getComplaintsForEmployee(@PathVariable("empId") int empId){
		
			return null;
	}
	
}
