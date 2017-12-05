package com.ksu.grad.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ksu.grad.entity.Complaint;
import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.pojo.ComplaintPOJO;
import com.ksu.grad.pojo.ReviewPOJO;

@Transactional
@Repository
public class ComplaintDAOImpl implements ComplaintDAO {
	private static final Logger logger = Logger.getLogger(ComplaintDAOImpl.class);
		
	private static final String SELECT_COMPLAINTS_BY_EMP_ID= 
			"SELECT a.* FROM EMAS.EmployeeHistory a INNER JOIN EMAS.AttributeStatus b"
			+ " ON a.AttributeStatusId = b.ID"
			+ " WHERE b.AttributeId = 4 "
			+ "AND a.EmployeeId = :empId";
	
	private static final String SELECT_ALL_COMPLAINTS =  
			"SELECT a.* FROM EMAS.EmployeeHistory a INNER JOIN EMAS.AttributeStatus b"
			+ " ON a.AttributeStatusId = b.ID"
			+ " WHERE b.AttributeId = 4";
	
	private static final String SELECT_COMPLAINTS_FOR_MANAGER_REVIEW =
			"SELECT a.* FROM EMAS.EmployeeHistory a INNER JOIN EMAS.AttributeStatus b"
			+ " ON a.AttributeStatusId = b.ID"
			+ " WHERE b.AttributeId = 4 "
            + " AND b.IsFinal = 0 " 
			+ " AND a.EmployeeId IN "
			+ "(SELECT DISTINCT EmployeeId FROM EMAS.EmployeeCorrelation WHERE ManagerId = :managerId)";
	
	@PersistenceContext
	EntityManager em;
	
	private static final String RESPONSE_COMPLAINT = "Complete";
	private static final String ESCALATE_COMPLAINT = "Escalate";

	/**
	 * this method gets back all the complaints for a specific employee in the employeehistory table
	 */
	@Override
	public List<EmployeeHistory> getAllComplaintsForEmployee(int empId) {

		Query q = em.createNativeQuery(SELECT_COMPLAINTS_BY_EMP_ID, EmployeeHistory.class);
		q.setParameter("empId", empId);
		List<EmployeeHistory> list = q.getResultList();
		
		if (list == null){
			return null;
		}
		
		return list;
	}
	
	
	/**
	 * this method gets back all complaints in employeehistory table
	 * @return
	 */
	@Override
	public List<EmployeeHistory> getAllComplaints() {

		Query q = em.createNativeQuery(SELECT_ALL_COMPLAINTS, EmployeeHistory.class);
		List<EmployeeHistory> list = q.getResultList();
		
		if (list == null){
			return null;
		}
		
		return list;
	}


	@Override
	public List<EmployeeHistory> getAllComplaintsUnderManager(int managerId) {
		Query q = em.createNativeQuery(SELECT_COMPLAINTS_FOR_MANAGER_REVIEW, EmployeeHistory.class);
		q.setParameter("managerId", managerId);
		List<EmployeeHistory> list = q.getResultList();
		
		if (list == null){
			return null;
		}
		
		return list;
	}
	
	@Override
	public Date fileComplaint(ComplaintPOJO complaint) {

		Date validFrom = null;
		try{

			StoredProcedureQuery q = em.createStoredProcedureQuery("CreateComplaintRequest")
							.registerStoredProcedureParameter("spResponse", java.sql.Timestamp.class, ParameterMode.OUT)
							.registerStoredProcedureParameter("JsonDetails", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("EmployeeFirstName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("EmployeeLastName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("ModifiedByFirstName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("ModifiedByLastName", String.class, ParameterMode.IN);
			
			q.setParameter("JsonDetails", complaint.getJsonDetails());
			q.setParameter("EmployeeFirstName", complaint.getEmployeeFirstName());
			q.setParameter("EmployeeLastName", complaint.getEmployeeLastName());
			q.setParameter("ModifiedByFirstName", complaint.getModifiedByFirstName());
			q.setParameter("ModifiedByLastName", complaint.getModifiedByLastName());

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
	public Date responseComplaint(ComplaintPOJO complaint) {
		Date validFrom = null;
		try{

			StoredProcedureQuery q = em.createStoredProcedureQuery("CreateComplaintResponse")
							.registerStoredProcedureParameter("spResponse", java.sql.Timestamp.class, ParameterMode.OUT)
							.registerStoredProcedureParameter("ResponseStatusLabel", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("JsonDetails", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("EmployeeFirstName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("EmployeeLastName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("ModifiedByFirstName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("ModifiedByLastName", String.class, ParameterMode.IN);
			
			q.setParameter("ResponseStatusLabel",RESPONSE_COMPLAINT);
			q.setParameter("JsonDetails", complaint.getJsonDetails());
			q.setParameter("EmployeeFirstName", complaint.getEmployeeFirstName());
			q.setParameter("EmployeeLastName", complaint.getEmployeeLastName());
			q.setParameter("ModifiedByFirstName", complaint.getModifiedByFirstName());
			q.setParameter("ModifiedByLastName", complaint.getModifiedByLastName());

			 q.execute();
			 
			 validFrom = (Date) q.getOutputParameterValue("spResponse");

		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		return validFrom;
	}

	/**
	 * escalate
	 * Response status label should be escalated
	 */
	@Override
	public Date escalateComplaint(ComplaintPOJO complaint) {
		Date validFrom = null;
		try{

			StoredProcedureQuery q = em.createStoredProcedureQuery("CreateComplaintResponse")
							.registerStoredProcedureParameter("spResponse", java.sql.Timestamp.class, ParameterMode.OUT)
							.registerStoredProcedureParameter("ResponseStatusLabel", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("JsonDetails", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("EmployeeFirstName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("EmployeeLastName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("ModifiedByFirstName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("ModifiedByLastName", String.class, ParameterMode.IN);
			
			q.setParameter("ResponseStatusLabel",ESCALATE_COMPLAINT);
			q.setParameter("JsonDetails", complaint.getJsonDetails());
			q.setParameter("EmployeeFirstName", complaint.getEmployeeFirstName());
			q.setParameter("EmployeeLastName", complaint.getEmployeeLastName());
			q.setParameter("ModifiedByFirstName", complaint.getModifiedByFirstName());
			q.setParameter("ModifiedByLastName", complaint.getModifiedByLastName());

			 q.execute();
			 
			 validFrom = (Date) q.getOutputParameterValue("spResponse");

		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		return validFrom;
	}

	

}
