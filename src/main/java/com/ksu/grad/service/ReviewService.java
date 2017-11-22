package com.ksu.grad.service;

import java.util.List;

import com.ksu.grad.entity.Review;

public interface ReviewService {

	public List<Review> getAllReviews();
	public Review getReviewById(int reviewId);
}
