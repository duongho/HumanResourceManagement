package com.ksu.grad.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksu.grad.dao.EmployeeDAO;
import com.ksu.grad.dao.LeaveDAO;
import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.pojo.LeavePOJO;

@Service
public class LeaveServiceImpl implements LeaveService {
	
	@Autowired
	public LeaveDAO leaveDao;
	
	@Autowired
	public EmployeeDAO empDao;

	public static String getTimeDiff(Date dateOne, Date dateTwo) {
	     String diff = "";
	     long timeDiff = Math.abs(dateOne.getTime() - dateTwo.getTime());
	     diff = String.format("%d hours", TimeUnit.MILLISECONDS.toHours(timeDiff),
	             TimeUnit.MILLISECONDS.toMinutes(timeDiff) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeDiff)));
	     return diff;
	}
	
	@Override
	public List<EmployeeHistory> displayLeaveRequest(int empId) {
		List<EmployeeHistory> lstEmployeeHistroy = leaveDao.getAllLeaveRequest(empId);
		return lstEmployeeHistroy;
	}
	@Override
	public List<EmployeeHistory> pendingDisplayLeaveRequest() {
		List<EmployeeHistory> lstEmployeeHistroy = leaveDao.displayPendingLeaveRequest();
		return lstEmployeeHistroy;
	}

	
	@Override
	public List<EmployeeHistory> getAllPendingEmpRequestForManager(int managerId) {

		return leaveDao.getAllPendingEmpRequestForManager(managerId);
	}

	@Override
	public Date requestLeave(LeavePOJO leaveRequest) {		
		return leaveDao.createLeave(leaveRequest);
	}
	
	
	@Override
	public Date approveLeave(LeavePOJO leaveRequest) {		
		return leaveDao.approveLeave(leaveRequest);
	}
	
	@Override
	public Date denyLeave(LeavePOJO leaveRequest) {		
		return leaveDao.denyLeave(leaveRequest);
	}
}
