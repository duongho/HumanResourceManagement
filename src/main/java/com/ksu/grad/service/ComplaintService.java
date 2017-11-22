package com.ksu.grad.service;

import java.util.List;

import com.ksu.grad.entity.Complaint;

public interface ComplaintService {

	public Complaint getComplaintById(int complaintId);
	
	public List<Complaint> getComplaintsForEmployee(int empId);
}
