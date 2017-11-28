package com.ksu.grad.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ksu.grad.entity.EmployeeHistory;
import com.ksu.grad.pojo.LeavePOJO;
import com.ksu.grad.service.LeaveService;

@Controller
@RequestMapping("api/leave")
public class LeaveController {
	
	@Autowired
	LeaveService leaveService;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	
	/**
	 * create a leave record by ultimately calling a store proc. The value being return is the validFrom column in the
	 * EmployeeHistory table. We are formatting it into a string for display purposes
	 * @param leaveRequest
	 * @return
	 */

	@RequestMapping(value="/request", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> requestLeave(@RequestBody LeavePOJO leaveRequest){		
		Date validFrom = leaveService.requestLeave(leaveRequest);		
		return new ResponseEntity<String>(sdf.format(validFrom), HttpStatus.OK);
	}
	
	/**
	 * Approve leave of a particular employee, similar to /request
	 * @param leaveStatus
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/approve", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> approveLeave(@RequestBody LeavePOJO leaveRequest){
		Date validFrom = leaveService.approveLeave(leaveRequest);		
		return new ResponseEntity<String>(sdf.format(validFrom), HttpStatus.OK);	
	}
	
	
	/**
	 * denying a leave request, similar to /request
	 * @param leaveRequest
	 * @return
	 */
	@RequestMapping(value="/deny", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> denyLeave(@RequestBody LeavePOJO leaveRequest){
		Date validFrom = leaveService.denyLeave(leaveRequest);		
		return new ResponseEntity<String>(sdf.format(validFrom), HttpStatus.OK);	
	}
	

	/**
	 * get all pending leave requests for the provided employee id
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/pending/employee/{empId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<EmployeeHistory>>   displayLeaveRequest(@PathVariable("empId") int id){
		List<EmployeeHistory> leaveRequestlst =  leaveService.displayLeaveRequest(id);
		return new ResponseEntity<List<EmployeeHistory>>(leaveRequestlst, HttpStatus.OK);		
	}
	
	/**
	 * get all pending leave requests for all employees
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/pending/all", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<EmployeeHistory>> displayPendingLeaveRequest(){
		List<EmployeeHistory> pendingLeaveRequestlst =  leaveService.pendingDisplayLeaveRequest();
		return new ResponseEntity<List<EmployeeHistory>>(pendingLeaveRequestlst, HttpStatus.OK);
	}
	
	

	
	/**
	 * get all pending leave requests for a given manager id
	 * @param leaveStatus
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/pending/manager/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<EmployeeHistory>> getAllEmployeePendingRequestForManager(@PathVariable("id") int managerId){
		List<EmployeeHistory> empHistories = leaveService.getAllPendingEmpRequestForManager(managerId);
		return new ResponseEntity<List<EmployeeHistory>>(empHistories, HttpStatus.OK);		
	}
}

