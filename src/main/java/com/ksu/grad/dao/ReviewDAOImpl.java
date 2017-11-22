package com.ksu.grad.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ksu.grad.entity.Review;

@Transactional
@Repository
public class ReviewDAOImpl implements ReviewDAO {
	
	
	private static final String SELECT_ALL_REVIEWS = "SELECT * FROM Review";
	private static final String SELECT_REVIEW_BY_ID ="SELECT * FROM Review WHERE ID= :reviewId";

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Review> getAllReviews() {
		Query q = entityManager.createNativeQuery(SELECT_ALL_REVIEWS, Review.class);
		List<Review> reviews = q.getResultList();
		return reviews;

	}

	@Override
	public Review getReviewById(int reviewId) {
		Query q = entityManager.createNativeQuery(SELECT_REVIEW_BY_ID, Review.class);
		q.setParameter("reviewId", reviewId);		
		
		List<Review> reviews =  q.getResultList();
		return reviews.get(0);
	}

}
