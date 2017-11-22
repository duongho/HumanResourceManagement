package com.ksu.grad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksu.grad.dao.ComplaintDAO;
import com.ksu.grad.entity.Complaint;

@Service
public class ComplaintServiceImpl implements ComplaintService {

	@Autowired
	ComplaintDAO dao;
	
	@Override
	public Complaint getComplaintById(int complaintId) {
		return dao.getComplaintById(complaintId);
	}

	@Override
	public List<Complaint> getComplaintsForEmployee(int empId) {
		return dao.getComplaintsForEmployee(empId);
	}
	
	

}
