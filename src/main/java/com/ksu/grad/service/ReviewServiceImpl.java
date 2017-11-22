package com.ksu.grad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksu.grad.dao.ReviewDAO;
import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.entity.Review;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	ReviewDAO reviewDAO;
	
	@Override
	public List<EmployeeHistory> getAllReviews() {
		
		return reviewDAO.getAllReviews();
	}

	@Override
	public Review getReviewById(int reviewId) {
		return reviewDAO.getReviewById(reviewId);
	}

	@Override
	public List<EmployeeHistory> getAllReviewsForEmployeeId(int empId) {
		return reviewDAO.getAllReviewsForEmployeeId(empId);
	}

}
