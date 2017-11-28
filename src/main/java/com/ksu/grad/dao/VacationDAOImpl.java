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

import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.pojo.VacationPOJO;

@Transactional
@Repository
public class VacationDAOImpl implements VacationDAO {
	
	private static final Logger logger = Logger.getLogger(VacationDAOImpl.class);

	private static final String APPROVE_VACATION = "Approved";
	
	private static final String DENY_VACATION = "Denied";
	
	private static final  String SELECT_ALL_EMPLOYEE_PENDING_REQUEST_FOR_MANAGER = "SELECT eh.* FROM EMAS.EmployeeHistory eh" + 
			" JOIN EMAS.AttributeStatus atst ON eh.AttributeStatusId = atst.id JOIN EMAS.Attribute a ON atst.AttributeId = a.id " + 
			" JOIN EMAS.Status s ON atst.StatusId = s.id WHERE a.Label = 'Vacation' AND atst.IsFinal =0 " +
			 "  AND eh.EmployeeId IN (SELECT DISTINCT EmployeeId FROM EMAS.EmployeeCorrelation WHERE ManagerId = :managerId)";
		
	
	private static final  String VACATION_REQUEST_FOR_EMPLOYEE = "SELECT eh.* FROM EMAS.EmployeeHistory eh " + 
			"INNER JOIN EMAS.AttributeStatus atst ON atst.id = eh.AttributeStatusId "
			+ "INNER JOIN EMAS.Attribute a ON a.id = atst.AttributeId " + 
			" INNER JOIN EMAS.Status s ON s.id = atst.StatusId WHERE eh.EmployeeId = :empId AND a.Label = 'Vacation' AND s.Label = 'Created'";	
	
	@PersistenceContext
	EntityManager em;

	@Override
	public Date requestVacation(VacationPOJO vacation) {
		Date validFrom = null;
		try{

			StoredProcedureQuery q = em.createStoredProcedureQuery("CreateVacationRequest")
							.registerStoredProcedureParameter("spResponse", java.sql.Timestamp.class, ParameterMode.OUT)
							.registerStoredProcedureParameter("JsonDetails", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("EmployeeFirstName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("EmployeeLastName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("ModifiedByFirstName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("ModifiedByLastName", String.class, ParameterMode.IN);
			
			q.setParameter("JsonDetails", vacation.getJsonDetails());
			q.setParameter("EmployeeFirstName", vacation.getEmployeeFirstName());
			q.setParameter("EmployeeLastName", vacation.getEmployeeLastName());
			q.setParameter("ModifiedByFirstName", vacation.getModifiedByFirstName());
			q.setParameter("ModifiedByLastName", vacation.getModifiedByLastName());

			 q.execute();
			 
			 java.sql.Timestamp ts = (java.sql.Timestamp) q.getOutputParameterValue("spResponse");
			 
			 validFrom = new Date(ts.getTime());

		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		return validFrom;
	}

	@Override
	public Date denyVacation(VacationPOJO vacation) {
		Date validFrom = null;
		try{

			StoredProcedureQuery q = em.createStoredProcedureQuery("CreateVacationResponse")
							.registerStoredProcedureParameter("spResponse", java.sql.Timestamp.class, ParameterMode.OUT)
							.registerStoredProcedureParameter("ResponseStatusLabel", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("JsonDetails", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("EmployeeFirstName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("EmployeeLastName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("ModifiedByFirstName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("ModifiedByLastName", String.class, ParameterMode.IN);
			
			q.setParameter("ResponseStatusLabel", DENY_VACATION);
			q.setParameter("JsonDetails", vacation.getJsonDetails());
			q.setParameter("EmployeeFirstName", vacation.getEmployeeFirstName());
			q.setParameter("EmployeeLastName", vacation.getEmployeeLastName());
			q.setParameter("ModifiedByFirstName", vacation.getModifiedByFirstName());
			q.setParameter("ModifiedByLastName", vacation.getModifiedByLastName());

			 q.execute();
			 
			 validFrom = (Date) q.getOutputParameterValue("spResponse");

		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		return validFrom;
	}
	
	

	@Override
	public Date approveVacation(VacationPOJO vacation) {
		Date validFrom = null;
		try{

			StoredProcedureQuery q = em.createStoredProcedureQuery("CreateVacationResponse")
							.registerStoredProcedureParameter("spResponse", java.sql.Timestamp.class, ParameterMode.OUT)
							.registerStoredProcedureParameter("ResponseStatusLabel", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("JsonDetails", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("EmployeeFirstName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("EmployeeLastName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("ModifiedByFirstName", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("ModifiedByLastName", String.class, ParameterMode.IN);
			
			q.setParameter("ResponseStatusLabel",APPROVE_VACATION);
			q.setParameter("JsonDetails", vacation.getJsonDetails());
			q.setParameter("EmployeeFirstName", vacation.getEmployeeFirstName());
			q.setParameter("EmployeeLastName", vacation.getEmployeeLastName());
			q.setParameter("ModifiedByFirstName", vacation.getModifiedByFirstName());
			q.setParameter("ModifiedByLastName", vacation.getModifiedByLastName());

			 q.execute();
			 
			 validFrom = (Date) q.getOutputParameterValue("spResponse");

		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		return validFrom;
	}
	
	
	@Override
	public List<EmployeeHistory> getAllPendingVacationRequestForManager(int managerId) {
		Query q = em.createNativeQuery(SELECT_ALL_EMPLOYEE_PENDING_REQUEST_FOR_MANAGER, EmployeeHistory.class);
		q.setParameter("managerId", managerId);
		List<EmployeeHistory> employeeHistory = q.getResultList();
		return employeeHistory;	
	}
	
	
	
	
	@Override
	public List<EmployeeHistory> getAllPendingVacationRequestForEmployee(int empId) {
		Query q = em.createNativeQuery(VACATION_REQUEST_FOR_EMPLOYEE, EmployeeHistory.class);
		q.setParameter("empId", empId);
		List<EmployeeHistory> employeeHistory = q.getResultList();
		if (employeeHistory == null){
			logger.debug("Found no vacation for this employee in the system");
			return null;
		}
		return employeeHistory;
	}
	

}
