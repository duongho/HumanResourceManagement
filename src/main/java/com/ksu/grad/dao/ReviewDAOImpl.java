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
import com.ksu.grad.pojo.ReviewPOJO;

@Transactional
@Repository
public class ReviewDAOImpl implements ReviewDAO {
	
	private static final Logger logger = Logger.getLogger(ReviewDAOImpl.class);
	
	private static final String SELECT_ALL_REVIEWS = 
							"SELECT a.* FROM EMAS.EmployeeHistory a INNER JOIN EMAS.AttributeStatus b "
						+ "ON a.AttributeStatusId = b.ID"
						+ " WHERE b.AttributeId = 5";	
	
	private static final String SELECT_ALL_REVIEWS_FOR_EMPLOYEE= 
							"SELECT a.* FROM EMAS.EmployeeHistory a "
							+ "INNER JOIN EMAS.AttributeStatus b "
							+ "ON a.AttributeStatusId = b.ID "
							+ "WHERE b.AttributeId = 5 "
							+ "AND a.EmployeeId = :empId";
	
	private static final String CREATE_REVIEW_STORE_PROC = "BEGIN EMAS.CreateEmployeeReview(?,?,?,?,?); END;";
	
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

	//if anything happen then the code will throw an exception. 
	@Override
	public boolean createReview(ReviewPOJO review) {
		boolean b = false;
		try{
			Query q = entityManager.createNativeQuery(CREATE_REVIEW_STORE_PROC)
	                .setParameter(1, review.getJsonDetails())
	                .setParameter(2, review.getEmployeeFirstName())
			        .setParameter(3, review.getEmployeeLastName())
			        .setParameter(4, review.getModifiedByFirstName())
			        .setParameter(5, review.getModifiedByLastName());

			 q.executeUpdate();
			b=true;
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		return b;
	}

}
