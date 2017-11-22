package com.ksu.grad.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ksu.grad.entity.Complaint;

@Transactional
@Repository
public class ComplaintDAOImpl implements ComplaintDAO {
	
	private static final String SELECT_COMPLAINT_BY_ID = "SELECT * FROM Complaint WHERE ID= :complaintId";
	private static final String SELECT_COMPLAINTS_BY_EMP_ID= "SELECT * FROM Complaint WHERE ";
	
	@PersistenceContext
	EntityManager em;

	@Override
	public Complaint getComplaintById(int complaintId) {
		Query q  = em.createNativeQuery(SELECT_COMPLAINT_BY_ID, Complaint.class);
		q.setParameter("complaintId", complaintId);
		List<Complaint> complaints = q.getResultList();
		
		if (complaints == null || complaints.isEmpty()){
			return null;
		}

		return complaints.get(0);
	}

	@Override
	public List<Complaint> getComplaintsForEmployee(int empId) {
		// TODO Auto-generated method stub
		return null;
	}

}
