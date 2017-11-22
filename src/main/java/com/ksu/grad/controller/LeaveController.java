package com.ksu.grad.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ksu.grad.entity.Leaves;
import com.ksu.grad.service.LeaveService;

@Controller
@RequestMapping("api/leave")
public class LeaveController {
	
	@Autowired
	LeaveService leaveService;

	@RequestMapping(value="/request/{empId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Leaves> requestLeave(@RequestBody String offFromDate, 
    		String offToDate, String offType,String justification, int managerId,@PathVariable int empId) throws ParseException{
		  Leaves leave = leaveService.leaverequest(offFromDate, offToDate, offType, justification, managerId, empId);
				  return new ResponseEntity<Leaves>(leave, HttpStatus.OK);
	}
}
