package com.ksu.grad.dao;

import java.util.List;

import com.ksu.grad.entity.Review;

public interface ReviewDAO {

	public List<Review> getAllReviews();
	public Review getReviewById(int reviewId);
}
