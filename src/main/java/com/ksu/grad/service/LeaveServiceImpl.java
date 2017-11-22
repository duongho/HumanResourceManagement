package com.ksu.grad.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksu.grad.dao.LeaveDAO;
import com.ksu.grad.entity.Leaves;

@Service
public class LeaveServiceImpl implements LeaveService {
	
	@Autowired
	public LeaveDAO leaveDao;

	@Override
	public Leaves leaverequest(String offFromDate, String offToDate, String offType, String justification,
			int managerId, int empId) throws ParseException {
		
		Leaves leaveobj = new Leaves();
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		
		if((offFromDate != null || ! offFromDate.equals(""))&&
				(offToDate != null || ! offToDate.equals(""))) {
			Date fromDate = format.parse(offFromDate);
			Date toDate = format.parse(offToDate);
			String diff = getTimeDiff(fromDate, toDate);			
			leaveobj.setLabel(diff);
			
		}		
		if(offType != null && ! offType.equals("")) {
			
		}
		if(justification != null && ! justification.equals("")) {
			
		}
		return leaveobj;
	}
	public static String getTimeDiff(Date dateOne, Date dateTwo) {
	     String diff = "";
	     long timeDiff = Math.abs(dateOne.getTime() - dateTwo.getTime());
	     diff = String.format("%d hours", TimeUnit.MILLISECONDS.toHours(timeDiff),
	             TimeUnit.MILLISECONDS.toMinutes(timeDiff) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeDiff)));
	     return diff;
	}
}
