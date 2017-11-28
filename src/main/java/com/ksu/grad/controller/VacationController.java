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
import com.ksu.grad.pojo.VacationPOJO;
import com.ksu.grad.service.VacationService;

@Controller
@RequestMapping("api/vacation")
public class VacationController {
	
	@Autowired
	VacationService service;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	
	/**
	 * create a leave record by ultimately calling a store proc. The value being return is the validFrom column in the
	 * EmployeeHistory table. We are formatting it into a string for display purposes
	 * @param leaveRequest
	 * @return
	 */

	@RequestMapping(value="/request", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> requestVacation(@RequestBody VacationPOJO vacation){		
		Date validFrom = service.requestVacation(vacation);		
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
	public ResponseEntity<String> approveVacation(@RequestBody VacationPOJO vacation){
		Date validFrom = service.approveVacation(vacation);		
		return new ResponseEntity<String>(sdf.format(validFrom), HttpStatus.OK);	
	}
	
	
	/**
	 * denying a leave request, similar to /request
	 * @param leaveRequest
	 * @return
	 */
	@RequestMapping(value="/deny", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> denyVacation(@RequestBody VacationPOJO vacation){
		Date validFrom = service.denyVacation(vacation);		
		return new ResponseEntity<String>(sdf.format(validFrom), HttpStatus.OK);	
	}
	

	/**
	 * get all pending vacations for an employee
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/pending/employee/{empId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<EmployeeHistory>>   getAllPendingVacationRequestForEmployee(@PathVariable("empId") int id){
		List<EmployeeHistory> unapproveVacations =  service.getAllPendingVacationRequestForEmployee(id);
		return new ResponseEntity<List<EmployeeHistory>>(unapproveVacations, HttpStatus.OK);		
	}
	

	/**
	 * get all pending vacation requests for a given manager id
	 * @param leaveStatus
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/pending/manager/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<EmployeeHistory>> getAllPendingVacationRequestForManager(@PathVariable("id") int managerId){
		List<EmployeeHistory> unapproveVacations = service.getAllPendingVacationRequestForManager(managerId);
		return new ResponseEntity<List<EmployeeHistory>>(unapproveVacations, HttpStatus.OK);		
	}
}
