package com.ksu.grad.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class LeaveDAOImpl implements LeaveDAO{

	@Override
	public boolean leaverequest(String offFromDate, String offToDate, String offType, String justification,
			int managerId, int empId) {
		// TODO Auto-generated method stub
		return false;
	}

}
