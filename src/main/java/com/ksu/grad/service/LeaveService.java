package com.ksu.grad.service;

import java.text.ParseException;

import org.springframework.web.bind.annotation.PathVariable;

import com.ksu.grad.entity.Leaves;

public interface LeaveService {
	
	Leaves leaverequest(String offFromDate, 
    		String offToDate, String offType,String justification, int managerId,int empId) throws ParseException;

}
