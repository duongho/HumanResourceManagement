package com.ksu.grad.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksu.grad.dao.EmployeeDAO;
import com.ksu.grad.dao.LeaveDAO;
import com.ksu.grad.entity.Attribute;
import com.ksu.grad.entity.AttributeStatus;
import com.ksu.grad.entity.Employee;
import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.entity.Leaves;
import com.ksu.grad.entity.Status;

@Service
public class LeaveServiceImpl implements LeaveService {
	
	@Autowired
	public LeaveDAO leaveDao;
	
	@Autowired
	public EmployeeDAO empDao;

	@Override
	public EmployeeHistory leaverequest(String offFromDate, String offToDate, int offType, String justification,
			int managerId, int empId) throws ParseException {
		 EmployeeHistory emplHistory = new EmployeeHistory();
		 AttributeStatus attrStatus = new AttributeStatus();
		 Attribute attribute = leaveDao.getAttributeforCreate();
		 Status status = (Status) leaveDao.getStatusforCreate();
		
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);	
		if((offFromDate != null || ! offFromDate.equals(""))&&
				(offToDate != null || ! offToDate.equals(""))) {
			Date fromDate = format.parse(offFromDate);
			Date toDate = format.parse(offToDate);
			String diff = getTimeDiff(fromDate, toDate);					
		}		
		if(offType != 0) {
			attrStatus.setAttribute(attribute);			
		}
		if(justification != null && ! justification.equals("")) {
			emplHistory.setInformation(justification);
		}
		attrStatus.setIsFinal(false);
		attrStatus.setStatus(status);
		Employee emp = empDao.getEmployeeById(empId);
		emplHistory.setEmployeeByEmployeeId(emp);
		emplHistory.setAttributeStatus(attrStatus);				
		return emplHistory;
	}
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
	public String approveLeaveRequest(int empId, String status) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<EmployeeHistory> getAllPendingEmpRequestForManager(int managerId) {

		return leaveDao.getAllPendingEmpRequestForManager(managerId);
	}
}
