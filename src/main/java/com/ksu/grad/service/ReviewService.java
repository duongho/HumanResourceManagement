package com.ksu.grad.service;

import java.util.List;

import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.entity.Review;
import com.ksu.grad.pojo.ReviewPOJO;

public interface ReviewService {

	public List<EmployeeHistory> getAllReviews();
	public List<EmployeeHistory> getAllReviewsForEmployeeId (int empId);
	public boolean createReview (ReviewPOJO review);
}
