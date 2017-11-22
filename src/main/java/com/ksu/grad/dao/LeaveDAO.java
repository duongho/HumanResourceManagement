package com.ksu.grad.dao;

public interface LeaveDAO {
	
	boolean leaverequest(String offFromDate, 
    		String offToDate, String offType,String justification, int managerId,int empId);

}
