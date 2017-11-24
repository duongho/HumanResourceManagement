package com.ksu.grad.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
	
	private static final  String APPROVE_LEAVE_REQUEST = "Update  ";
	
	private static final  String ATTRIBUTE_FOR_CREATE_LEAVE ="SELECT * from Attribute a where a.id=3 ";
	private static final  String STATUS_FOR_CREATE_LEAVE ="SELECT * from Status s where s.id=5 ";
	
	private static final String CREATE_LEAVE_STORE_PROC = "BEGIN EMAS.CreateEmployeeLeave(?,?,?,?,?); END;";
	
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
	public boolean approveLeaveRequest(){
		boolean status = true;
		Query q = entityManager.createNativeQuery(APPROVE_LEAVE_REQUEST, EmployeeHistory.class);
		 q.executeUpdate();
		return status;	
	}
/**
 * get Attibute object for creating leave
 * return attibute Obj with "Sick Leave" attribute
 */
	@Override
	public Attribute getAttributeforCreate() {
		Query q = entityManager.createNativeQuery(ATTRIBUTE_FOR_CREATE_LEAVE, Attribute.class);
		Attribute attr = (Attribute) q.getResultList().get(0);
		return attr;
	}
	/**
	 * get Status for creating Leave.
	 * return status Object with "Created" status
	 * 
	 */
	@Override
	public Status getStatusforCreate() {
		Query q = entityManager.createNativeQuery(STATUS_FOR_CREATE_LEAVE, Status.class);
		Status status = (Status) q.getResultList().get(0);
		return status;
	}
 
	@Override
	public List<EmployeeHistory> getAllPendingEmpRequestForManager(int managerId) {
		Query q = entityManager.createNativeQuery(SELECT_ALL_EMPLOYEE_PENDING_REQUEST_FOR_MANAGER, EmployeeHistory.class);
		q.setParameter("managerId", managerId);
		List<EmployeeHistory> employeeHistory = q.getResultList();
		return employeeHistory;	
	}
	/**
	 * Create Leave
	 * @param leave POJO
	 * return boolean
	 */

	@Override
	public boolean createLeave(LeavePOJO leave) {
		boolean b = false;
		try{
			Query q = entityManager.createNativeQuery(CREATE_LEAVE_STORE_PROC)
	                .setParameter(1, leave.getJsonDetails())
	                .setParameter(2, leave.getEmployeeFirstName())
			        .setParameter(3, leave.getEmployeeLastName())
			        .setParameter(4, leave.getModifiedByFirstName())
			        .setParameter(5, leave.getModifiedByLastName());

			 q.executeUpdate();
			b=true;
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return b;
	}	

}
