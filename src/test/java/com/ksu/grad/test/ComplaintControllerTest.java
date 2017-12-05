package com.ksu.grad.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ksu.grad.controller.ComplaintController;
import com.ksu.grad.controller.HumanResourceController;
import com.ksu.grad.pojo.ComplaintPOJO;
import com.ksu.grad.pojo.ReviewPOJO;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ComplaintControllerTest {
	@Autowired
	private ComplaintController complaintController;
	@Autowired
	private HumanResourceController humanController;
	
	/**
	 * test case for getting complaints from an employee
	 *//*
	@Test
	public void getComplaintsForEmployeeTest() {
		ResponseEntity result = complaintController.getComplaintsForEmployee(6);
		assertNotNull(result);
		assertEquals(result.getStatusCode(), HttpStatus.OK);		
	}
	
	@Test
	public void getAllComplaintsTest() {
		ResponseEntity result = complaintController. getAllComplaints();
		assertNotNull(result);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void getAllNewComplaintsForManagerReviewTest() {
		ResponseEntity result = complaintController.getAllNewComplaintsForManagerReview(7);
		result=null;
		assertNull(result);
		
	}*/
	
	/*@Test		
	public void createComplaintTest() {
		ComplaintPOJO complaintPojo  = new ComplaintPOJO();		
		complaintPojo.setEmployeeFirstName("Bob");
		complaintPojo.setEmployeeLastName("Smith");
		complaintPojo.setModifiedByFirstName("Bob");
		complaintPojo.setModifiedByLastName("Smith");
		complaintPojo.setJsonDetails("testing with new complaint");
		ResponseEntity result =  complaintController.createComplaint(complaintPojo);
		assertNotNull(result);
		assertEquals(result.getStatusCode(), HttpStatus.OK);		
	}*/
	@Test		
	public void escalateComplaintTest() {
		ComplaintPOJO complaintPojo  = new ComplaintPOJO();		
		complaintPojo.setEmployeeFirstName("Bob");
		complaintPojo.setEmployeeLastName("Smith");
		complaintPojo.setModifiedByFirstName("Bob");
		complaintPojo.setModifiedByLastName("Smith");
		complaintPojo.setJsonDetails("escalate");
		ResponseEntity result =  humanController.escalateComplaint(complaintPojo);
		assertNotNull(result);
		assertEquals(result.getStatusCode(), HttpStatus.OK);		
	}
}
