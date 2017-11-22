package com.ksu.grad.dao;

import java.util.List;

import com.ksu.grad.entity.Complaint;

public interface ComplaintDAO {

	public Complaint getComplaintById(int complaintId);
	
	public List<Complaint> getComplaintsForEmployee(int empId);
}
