package com.ksu.grad.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.entity.Leaves;
import com.ksu.grad.service.LeaveService;

@Controller
@RequestMapping("api/leave")
public class LeaveController {
	
	@Autowired
	LeaveService leaveService;

	@RequestMapping(value="/request/{empId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<EmployeeHistory> requestLeave(@RequestParam String offFromDate, 
    		@RequestParam  String offToDate, @RequestParam int offType,@RequestParam String justification, 
    		@RequestParam int managerId,@PathVariable int empId) throws ParseException{
		EmployeeHistory empHistory = leaveService.leaverequest(offFromDate, offToDate, offType, justification, managerId, empId);
				  return new ResponseEntity<EmployeeHistory>(empHistory, HttpStatus.OK);
	}
	

	/**
	 * get all pending leave requests for the provided employee id
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/displayLeave/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<EmployeeHistory>>   displayLeaveRequest(@PathVariable int id){
		List<EmployeeHistory> leaveRequestlst =  leaveService.displayLeaveRequest(id);
		return new ResponseEntity<List<EmployeeHistory>>(leaveRequestlst, HttpStatus.OK);		
	}
	
	/**
	 * get all pending leave requests for all employees
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/pendingLeaveRequest", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<EmployeeHistory>> displayPendingLeaveRequest(){
		List<EmployeeHistory> pendingLeaveRequestlst =  leaveService.pendingDisplayLeaveRequest();
		return new ResponseEntity<List<EmployeeHistory>>(pendingLeaveRequestlst, HttpStatus.OK);
	}
	
	@RequestMapping(value="/approveLeave/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> approveLeave(@RequestParam String leaveStatus,@PathVariable int id){
		String status = leaveService.approveLeaveRequest(id, leaveStatus);
		return new ResponseEntity<String>(status, HttpStatus.OK);		
	}
	
	/**
	 * get all pending leave requests for a given manager id
	 * @param leaveStatus
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/manager/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<EmployeeHistory>> getAllEmployeePendingRequestForManager(@PathVariable("id") int managerId){
		List<EmployeeHistory> empHistories = leaveService.getAllPendingEmpRequestForManager(managerId);
		return new ResponseEntity<List<EmployeeHistory>>(empHistories, HttpStatus.OK);		
	}
}

