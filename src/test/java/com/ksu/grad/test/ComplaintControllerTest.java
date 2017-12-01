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

@SpringBootTest
@RunWith(SpringRunner.class)
public class ComplaintControllerTest {
	@Autowired
	private ComplaintController complaintController;
	
	/**
	 * test case for getting complaints from an employee
	 */
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
		
	}
}
