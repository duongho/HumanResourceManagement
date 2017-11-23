package com.ksu.grad.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.ksu.grad.entity.Attribute;
import com.ksu.grad.entity.Employee;
import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.entity.Leaves;
import com.ksu.grad.entity.Status;


@Transactional
@Repository
public class LeaveDAOImpl implements LeaveDAO{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	private static final  String LEAVE_REQUEST_FOR_EMPLOYEE = "SELECT eh.* FROM EMAS.EmployeeHistory eh" + 
			"JOIN EMAS.AttributeStatus atst ON eh.AttributeStatusId = atst.id JOIN EMAS.Attribute a ON atst.AttributeId = a.id" + 
			"JOIN EMAS.Status s ON atst.StatusId = s.id WHERE eh.EmployeeId = ? AND a.Label = 'Sick Leave' AND s.Label = 'Created'";		
	
	
	private static final  String PENDING_LEAVE_REQUEST_FOR_ALL_EMPLOYEES = "SELECT eh.* FROM EMAS.EmployeeHistory eh" + 
			"JOIN EMAS.AttributeStatus atst ON eh.AttributeStatusId = atst.id JOIN EMAS.Attribute a ON atst.AttributeId = a.id" + 
			"JOIN EMAS.Status s ON atst.StatusId = s.id WHERE a.Label = 'Sick Leave' AND atst.IsFinal =0";
	
	private static final  String SELECT_ALL_EMPLOYEE_PENDING_REQUEST_FOR_MANAGER = "SELECT eh.* FROM EMAS.EmployeeHistory eh" + 
			" JOIN EMAS.AttributeStatus atst ON eh.AttributeStatusId = atst.id JOIN EMAS.Attribute a ON atst.AttributeId = a.id " + 
			" JOIN EMAS.Status s ON atst.StatusId = s.id WHERE a.Label = 'Sick Leave' AND atst.IsFinal =0 " +
			 "  AND eh.EmployeeId IN (SELECT DISTINCT EmployeeId FROM EMAS.EmployeeCorrelation WHERE ManagerId = :managerId)";
	
	private static final  String APPROVE_LEAVE_REQUEST = "Update  ";
	
	private static final  String ATTRIBUTE_FOR_CREATE_LEAVE ="SELECT * from Attibute a where a.id=3 ";
	private static final  String STATUS_FOR_CREATE_LEAVE ="SELECT * from Status s where s.id=5 ";

	@Override
	public boolean leaverequest(EmployeeHistory emplHistory) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(EmployeeHistory.class,"emplHistoryClass");
		session.save(emplHistory);
		return true;
	}
	
	/**
	 * get all leave request for an emplyee
	 */
	@Override
	public List<EmployeeHistory> getAllLeaveRequest(int empId) {
		Query q = entityManager.createNativeQuery(LEAVE_REQUEST_FOR_EMPLOYEE, EmployeeHistory.class);
		q.setParameter("empId", empId);
		List<EmployeeHistory> employeeHistory = q.getResultList();
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

	@Override
	public Attribute getAttributeforCreate() {
		Query q = entityManager.createNativeQuery(ATTRIBUTE_FOR_CREATE_LEAVE, Attribute.class);
		Attribute attr = (Attribute) q.getResultList().get(0);
		return attr;
	}
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
	
	/*public List getStatusId() {
		Session session = (Session) entityManager.getDelegate();
		Query query = (Query) session.createSQLQuery("CALL EMAS.GetRequestsByAttributeLabel(?,?,?)").addEntity(Stock.class)
				.setParameter("stockCode", "7277");
		return null;
		
	}*/
	

}
