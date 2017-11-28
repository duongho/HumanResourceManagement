package com.ksu.grad.service;

import java.util.Date;
import java.util.List;

import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.pojo.ReviewPOJO;

public interface ReviewService {

	public List<EmployeeHistory> getAllReviews();
	public List<EmployeeHistory> getAllReviewsForEmployeeId (int empId);
	public Date createReview (ReviewPOJO review);
	
	public List<EmployeeHistory> getAllEmpNewReviewForManager(int managerId);
	
	public Date responseReview(ReviewPOJO review);
}
