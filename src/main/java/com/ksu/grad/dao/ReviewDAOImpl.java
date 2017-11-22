package com.ksu.grad.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.entity.Review;

@Transactional
@Repository
public class ReviewDAOImpl implements ReviewDAO {
	
	private static final Logger logger = Logger.getLogger(ReviewDAOImpl.class);
	
	private static final String SELECT_ALL_REVIEWS = 
							"SELECT a.* FROM EMAS.EmployeeHistory a INNER JOIN EMAS.AttributeStatus b "
						+ "ON a.AttributeStatusId = b.ID"
						+ " WHERE b.AttributeId = 5";
	
	
	private static final String SELECT_REVIEW_BY_ID ="SELECT * FROM Review WHERE ID= :reviewId";
	
	
	private static final String SELECT_ALL_REVIEWS_FOR_EMPLOYEE= 
							"SELECT a.* FROM EMAS.EmployeeHistory a "
							+ "INNER JOIN EMAS.AttributeStatus b "
							+ "ON a.AttributeStatusId = b.ID "
							+ "WHERE b.AttributeId = 5 "
							+ "AND a.EmployeeId = :empId";

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<EmployeeHistory> getAllReviews() {
		Query q = entityManager.createNativeQuery(SELECT_ALL_REVIEWS, EmployeeHistory.class);
		List<EmployeeHistory> reviews = q.getResultList();
		if (reviews == null || reviews.isEmpty()){
			logger.debug("Found no review in the system");
			return null;
		}
		return reviews;

	}

	@Override
	public Review getReviewById(int reviewId) {
		Query q = entityManager.createNativeQuery(SELECT_REVIEW_BY_ID, Review.class);
		q.setParameter("reviewId", reviewId);		
		
		List<Review> reviews =  q.getResultList();
		if (reviews == null || reviews.isEmpty()){
			logger.debug("Found no review in the system");
			return null;
		}
		return reviews.get(0);
	}

	@Override
	public List<EmployeeHistory> getAllReviewsForEmployeeId(int empId) {
		Query q = entityManager.createNativeQuery(SELECT_ALL_REVIEWS_FOR_EMPLOYEE, EmployeeHistory.class);
		q.setParameter("empId", empId);
		
		List<EmployeeHistory> reviewHistoryForEmployee = q.getResultList();
		
		if (reviewHistoryForEmployee == null){
			logger.debug("Found no review in the system");
			return null;
		}
		
		return reviewHistoryForEmployee;
	}

}
