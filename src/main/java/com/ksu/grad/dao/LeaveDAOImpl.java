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
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.ksu.grad.entity.Attribute;
import com.ksu.grad.entity.Employee;
import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.entity.Leaves;
import com.ksu.grad.entity.Status;
import com.ksu.grad.pojo.LeavePOJO;
import com.ksu.grad.pojo.ReviewPOJO;


@Transactional
@Repository
public class LeaveDAOImpl implements LeaveDAO{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Logger logger = Logger.getLogger(LeaveDAOImpl.class);
	
	private static final  String LEAVE_REQUEST_FOR_EMPLOYEE = "SELECT eh.* FROM EMAS.EmployeeHistory eh " + 
			"INNER JOIN EMAS.AttributeStatus atst ON atst.id = eh.AttributeStatusId "
			+ "INNER JOIN EMAS.Attribute a ON a.id = atst.AttributeId " + 
			" INNER JOIN EMAS.Status s ON s.id = atst.StatusId WHERE eh.EmployeeId = ? AND a.Label = 'Sick Leave' AND s.Label = 'Created'";		
	
	
	private static final  String PENDING_LEAVE_REQUEST_FOR_ALL_EMPLOYEES = "SELECT eh.* FROM EMAS.EmployeeHistory eh " + 
			"JOIN EMAS.AttributeStatus atst ON eh.AttributeStatusId = atst.id JOIN EMAS.Attribute a ON atst.AttributeId = a.id " + 
			"JOIN EMAS.Status s ON atst.StatusId = s.id WHERE a.Label = 'Sick Leave' AND atst.IsFinal =0";
	
	private static final  String SELECT_ALL_EMPLOYEE_PENDING_REQUEST_FOR_MANAGER = "SELECT eh.* FROM EMAS.EmployeeHistory eh" + 
			" JOIN EMAS.AttributeStatus atst ON eh.AttributeStatusId = atst.id JOIN EMAS.Attribute a ON atst.AttributeId = a.id " + 
			" JOIN EMAS.Status s ON atst.StatusId = s.id WHERE a.Label = 'Sick Leave' AND atst.IsFinal =0 " +
			 "  AND eh.EmployeeId IN (SELECT DISTINCT EmployeeId FROM EMAS.EmployeeCorrelation WHERE ManagerId = :managerId)";
	

	private static final String APPROVE_LEAVE = "Approved";
	private static final String  DENY_LEAVE = "Denied";
		
	/**
	 * get all leave request for an emplyee
	 */
	@Override
	public List<EmployeeHistory> getAllLeaveRequest(int empId) {
		Query q = entityManager.createNativeQuery(LEAVE_REQUEST_FOR_EMPLOYEE, EmployeeHistory.class);
		q.setParameter(1, empId);
		List<EmployeeHistory> employeeHistory = q.getResultList();
		if (employeeHistory == null){
			logger.debug("Found no leave in the system");
			return null;
		}
		return employeeHistory;
	}
	
	/**
	 * this actually get back all pending leave requests for all employees
	 */
	@Override
	public List<EmployeeHistory> displayPendingLeaveRequest(){
		Query q = entityManager.createNativeQuery(PENDING_LEAVE_REQUEST_FOR_ALL_EMPLOYEES, EmployeeHistory.class);
		List<EmployeeHistory> employeeHistory = q.getResultList();
		return employeeHistory;	
	}
	
 
	@Override
	public List<EmployeeHistory> getAllPendingEmpRequestForManager(int managerId) {
		Query q = entityManager.createNativeQuery(SELECT_ALL_EMPLOYEE_PENDING_REQUEST_FOR_MANAGER, EmployeeHistory.class);
		q.setParameter("managerId", managerId);
		List<EmployeeHistory> employeeHistory = q.getResultList();
		return employeeHistory;	
	}

	
	/**
	 * calling store proc CreateLeaveRequest to create a leave record
	 */
	@Override
	public Date createLeave(LeavePOJO leave) {
		Date validFrom = null;
		try{

			StoredProcedureQuery q = entityManager.createStoredProcedureQuery("CreateLeaveRequest")
							.registerStoredProcedureParameter("spResponse", java.sql.Timestamp.class, ParameterMode.OUT)
							.registerStoredProcedureParameter("JsonDetails", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("EmployeeFirstName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("EmployeeLastName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("ModifiedByFirstName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("ModifiedByLastName", String.class, ParameterMode.IN);
			
			q.setParameter("JsonDetails", leave.getJsonDetails());
			q.setParameter("EmployeeFirstName", leave.getEmployeeFirstName());
			q.setParameter("EmployeeLastName", leave.getEmployeeLastName());
			q.setParameter("ModifiedByFirstName", leave.getModifiedByFirstName());
			q.setParameter("ModifiedByLastName", leave.getModifiedByLastName());

			 q.execute();
			 
			 java.sql.Timestamp ts = (java.sql.Timestamp) q.getOutputParameterValue("spResponse");
			 
			 validFrom = new Date(ts.getTime());

		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		return validFrom;
	}

	/**
	 * this method is strictly to approve Leave. Use DenyLeave if we want to deny a leave request
	 */
	@Override
	public Date approveLeave(LeavePOJO leave) {
		Date validFrom = null;
		try{

			StoredProcedureQuery q = entityManager.createStoredProcedureQuery("CreateLeaveResponse")
							.registerStoredProcedureParameter("spResponse", java.sql.Timestamp.class, ParameterMode.OUT)
							.registerStoredProcedureParameter("ResponseStatusLabel", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("JsonDetails", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("EmployeeFirstName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("EmployeeLastName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("ModifiedByFirstName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("ModifiedByLastName", String.class, ParameterMode.IN);
			
			q.setParameter("ResponseStatusLabel",APPROVE_LEAVE);
			q.setParameter("JsonDetails", leave.getJsonDetails());
			q.setParameter("EmployeeFirstName", leave.getEmployeeFirstName());
			q.setParameter("EmployeeLastName", leave.getEmployeeLastName());
			q.setParameter("ModifiedByFirstName", leave.getModifiedByFirstName());
			q.setParameter("ModifiedByLastName", leave.getModifiedByLastName());

			 q.execute();
			 
			 validFrom = (Date) q.getOutputParameterValue("spResponse");

		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		return validFrom;
	}	
	
	
	
	@Override
	public Date denyLeave(LeavePOJO leave) {
		Date validFrom = null;
		try{

			StoredProcedureQuery q = entityManager.createStoredProcedureQuery("CreateLeaveResponse")
							.registerStoredProcedureParameter("spResponse", java.sql.Timestamp.class, ParameterMode.OUT)
							.registerStoredProcedureParameter("ResponseStatusLabel", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("JsonDetails", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("EmployeeFirstName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("EmployeeLastName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("ModifiedByFirstName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("ModifiedByLastName", String.class, ParameterMode.IN);
			
			q.setParameter("ResponseStatusLabel", DENY_LEAVE);
			q.setParameter("JsonDetails", leave.getJsonDetails());
			q.setParameter("EmployeeFirstName", leave.getEmployeeFirstName());
			q.setParameter("EmployeeLastName", leave.getEmployeeLastName());
			q.setParameter("ModifiedByFirstName", leave.getModifiedByFirstName());
			q.setParameter("ModifiedByLastName", leave.getModifiedByLastName());

			 q.execute();
			 
			 validFrom = (Date) q.getOutputParameterValue("spResponse");

		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		return validFrom;
	}	

}
