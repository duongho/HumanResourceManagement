package com.ksu.grad.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ksu.grad.controller.ReviewController;
import com.ksu.grad.pojo.LeavePOJO;
import com.ksu.grad.pojo.ReviewPOJO;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReviewControllerTest {
	@Autowired
	private ReviewController reviewController;
	
	
	@Test
	public void leaveRequest() {
		
		ResponseEntity result =  reviewController.getAllReviewsForEmployeeId(100);
		assertNotNull(result);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		
		}
	@Test
	public void getAllEmployeesNewReviewUnderManagerTest() {
		
		ResponseEntity result =  reviewController.getAllEmployeesNewReviewUnderManager(10000);
		assertNotNull(result);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		
		}
	@Test		
	public void createReviewTest() {
		ReviewPOJO reviewPojo = new ReviewPOJO();		
		reviewPojo.setEmployeeFirstName("");
		reviewPojo.setEmployeeLastName("Smith");
		reviewPojo.setModifiedByFirstName("Bob");
		reviewPojo.setModifiedByLastName("Smith");
		reviewPojo.setJsonDetails("testing with null string employee first name");
		ResponseEntity result =  reviewController.createReview(reviewPojo);
		assertNotNull(result);
		assertEquals(result.getStatusCode(), HttpStatus.OK);		
	}
	@Test		
	public void reviewresponseTest() {
		ReviewPOJO reviewPojo = new ReviewPOJO();		
		reviewPojo.setEmployeeFirstName("Bob");
		reviewPojo.setEmployeeLastName("Smith");
		reviewPojo.setModifiedByFirstName("Marie");
		reviewPojo.setModifiedByLastName("Smith");
		reviewPojo.setJsonDetails("");
		ResponseEntity result =  reviewController.responseReview(reviewPojo);
		assertNotNull(result);
		assertEquals(result.getStatusCode(), HttpStatus.OK);		
	}
	

}
