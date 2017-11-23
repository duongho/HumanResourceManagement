package com.ksu.grad.dao;

import java.util.List;

import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.entity.Review;
import com.ksu.grad.pojo.ReviewPOJO;

public interface ReviewDAO {

	public List<EmployeeHistory> getAllReviews();
	public List<EmployeeHistory> getAllReviewsForEmployeeId(int empId);
	public boolean createReview(ReviewPOJO review);
}
