package com.ksu.grad.service;

import java.util.List;

import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.entity.Review;

public interface ReviewService {

	public List<EmployeeHistory> getAllReviews();
	public Review getReviewById(int reviewId);
	public List<EmployeeHistory> getAllReviewsForEmployeeId (int empId);
}
