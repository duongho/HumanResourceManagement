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

import com.ksu.grad.entity.Review;
import com.ksu.grad.service.ReviewService;

@Controller
@RequestMapping("review")
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@GetMapping("all")
	public ResponseEntity<List<Review>> getAllPerson() {
		List<Review> list = reviewService.getAllReviews();
		
		return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="{reviewId}", method=RequestMethod.GET)
	public ResponseEntity<Review> getReview(@PathVariable ("reviewId") int reviewId){
		
		Review review = reviewService.getReviewById(reviewId);
		
		return new ResponseEntity<Review>(review, HttpStatus.OK);
	}
}
