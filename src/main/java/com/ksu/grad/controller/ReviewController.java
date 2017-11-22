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

import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.entity.Review;
import com.ksu.grad.service.ReviewService;

@Controller
@RequestMapping("api/review")
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@GetMapping("all")
	public ResponseEntity<List<EmployeeHistory>> getAllReviews(){
		List<EmployeeHistory> list = reviewService.getAllReviews();
		
		return new ResponseEntity<List<EmployeeHistory>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value="employee/{empId}/all", method=RequestMethod.GET)
	public ResponseEntity<List<EmployeeHistory>> getAllReviewsForEmployeeId(@PathVariable("empId") int empId){
		
		List<EmployeeHistory> empHistoryList = reviewService.getAllReviewsForEmployeeId(empId);
		return new ResponseEntity<List<EmployeeHistory>>(empHistoryList, HttpStatus.OK);
	}
}
