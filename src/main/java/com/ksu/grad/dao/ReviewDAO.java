package com.ksu.grad.dao;

import java.util.Date;
import java.util.List;

import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.pojo.ReviewPOJO;

public interface ReviewDAO {

	public List<EmployeeHistory> getAllReviews();
	public List<EmployeeHistory> getAllReviewsForEmployeeId(int empId);
	public List<EmployeeHistory> getAllEmpNewReviewForManager(int managerId);
	public Date createReview(ReviewPOJO review);
	public Integer responseReview(ReviewPOJO review);
}
