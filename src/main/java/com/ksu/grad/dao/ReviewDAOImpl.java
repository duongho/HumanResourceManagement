package com.ksu.grad.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
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
	
	private static final String SELECT_ALL_EMP_REVIEWS_FOR_MANAGER=
					"SELECT a.* FROM EMAS.EmployeeHistory a "
					+ "INNER JOIN EMAS.AttributeStatus b "
					+ "ON a.AttributeStatusId = b.ID "
					+ "WHERE b.AttributeId = 5 "
					+ " AND b.IsFinal =0 "
					+ " AND a.EmployeeId "
					+ "IN (SELECT DISTINCT EmployeeId FROM EMAS.EmployeeCorrelation WHERE ManagerId = :managerId)";	
	
	private static final String CREATE_REVIEW_STORE_PROC = "BEGIN EMAS.CreateEmployeeReview(?,?,?,?,?); END;";
	
	
	private static final String RESPONSE_REVIEW = "Complete";

	
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
	
	
	@Override
	public List<EmployeeHistory> getAllEmpNewReviewForManager(int managerId) {
		Query q = entityManager.createNativeQuery(SELECT_ALL_EMP_REVIEWS_FOR_MANAGER, EmployeeHistory.class);
		q.setParameter("managerId", managerId);
		
		List<EmployeeHistory> reviewHistoryForEmployee = q.getResultList();
		
		if (reviewHistoryForEmployee == null){
			logger.debug("Found no review in the system");
			return null;
		}
		
		return reviewHistoryForEmployee;
	}

//	//if anything happen then the code will throw an exception. 
//	@Override
//	public boolean createReview(ReviewPOJO review) {
//		boolean b = false;
//		try{
//			Query q = entityManager.createNativeQuery(CREATE_REVIEW_STORE_PROC)
//	                .setParameter(1, review.getJsonDetails())
//	                .setParameter(2, review.getEmployeeFirstName())
//			        .setParameter(3, review.getEmployeeLastName())
//			        .setParameter(4, review.getModifiedByFirstName())
//			        .setParameter(5, review.getModifiedByLastName());
//
//			 q.executeUpdate();
//			b=true;
//		}catch(Exception e){
//			logger.error(e.getMessage());
//		}
//		
//		return b;
//	}
	
	//if anything happen then the code will throw an exception. 
	@Override
	public Date createReview(ReviewPOJO review) {
		Date validFrom = null;
		try{

			StoredProcedureQuery q = entityManager.createStoredProcedureQuery("CreateReviewRequest")
							.registerStoredProcedureParameter("spResponse", java.sql.Timestamp.class, ParameterMode.OUT)
							.registerStoredProcedureParameter("JsonDetails", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("EmployeeFirstName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("EmployeeLastName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("ModifiedByFirstName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("ModifiedByLastName", String.class, ParameterMode.IN);
			
			q.setParameter("JsonDetails", review.getJsonDetails());
			q.setParameter("EmployeeFirstName", review.getEmployeeFirstName());
			q.setParameter("EmployeeLastName", review.getEmployeeLastName());
			q.setParameter("ModifiedByFirstName", review.getModifiedByFirstName());
			q.setParameter("ModifiedByLastName", review.getModifiedByLastName());

			 q.execute();
			 
			 java.sql.Timestamp ts = (java.sql.Timestamp) q.getOutputParameterValue("spResponse");
			 
			 validFrom = new Date(ts.getTime());

		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		return validFrom;
	}

	/**
	 * completing a review.
	 * Response status label should be completed
	 */
	@Override
	public Integer responseReview(ReviewPOJO review) {
		Integer empHistoryId = null;
		try{

			StoredProcedureQuery q = entityManager.createStoredProcedureQuery("CreateReviewRequest")
							.registerStoredProcedureParameter("spResponse", Integer.class, ParameterMode.OUT)
							.registerStoredProcedureParameter("ResponseStatusLabel", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("JsonDetails", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("EmployeeFirstName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("EmployeeLastName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("ModifiedByFirstName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("ModifiedByLastName", String.class, ParameterMode.IN);
			
			q.setParameter("ResponseStatusLabel",RESPONSE_REVIEW);
			q.setParameter("JsonDetails", review.getJsonDetails());
			q.setParameter("EmployeeFirstName", review.getEmployeeFirstName());
			q.setParameter("EmployeeLastName", review.getEmployeeLastName());
			q.setParameter("ModifiedByFirstName", review.getModifiedByFirstName());
			q.setParameter("ModifiedByLastName", review.getModifiedByLastName());

			 q.execute();
			 
			 empHistoryId = (Integer) q.getOutputParameterValue("spResponse");

		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		return empHistoryId;
	}



}
