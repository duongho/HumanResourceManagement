package com.ksu.grad.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ksu.grad.entity.EmployeeHistory;

@Transactional
@Repository
public class ComplaintDAOImpl implements ComplaintDAO {
		
	private static final String SELECT_COMPLAINTS_BY_EMP_ID= 
			"SELECT a.* FROM EMAS.EmployeeHistory a INNER JOIN EMAS.AttributeStatus b"
			+ " ON a.AttributeStatusId = b.ID"
			+ " WHERE b.AttributeId = 4 "
			+ "AND a.EmployeeId = :empId";
	
	private static final String SELECT_ALL_COMPLAINTS =  
			"SELECT a.* FROM EMAS.EmployeeHistory a INNER JOIN EMAS.AttributeStatus b"
			+ " ON a.AttributeStatusId = b.ID"
			+ " WHERE b.AttributeId = 4";
	
	@PersistenceContext
	EntityManager em;


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
	
	

}
