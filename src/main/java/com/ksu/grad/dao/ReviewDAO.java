package com.ksu.grad.dao;

import java.util.List;

import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.entity.Review;

public interface ReviewDAO {

	public List<EmployeeHistory> getAllReviews();
	public Review getReviewById(int reviewId);
	public List<EmployeeHistory> getAllReviewsForEmployeeId(int empId);
}
