package com.ksu.grad.test;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.ksu.grad.controller.LeaveController;
import com.ksu.grad.pojo.LeavePOJO;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LeaveControllerTest {
	@Autowired
	private LeaveController leaveController;
	
	
	
	/** 
	 * test with null values.
	 */
	@Test
	public void leaveRequestTest() {
		LeavePOJO leavePojo = new LeavePOJO();
		leavePojo.setEmployeeFirstName("Albert");
		leavePojo.setEmployeeLastName("Maine");
		leavePojo.setModifiedByFirstName("Bob");
		leavePojo.setModifiedByLastName("Smith");
		leavePojo.setJsonDetails("leave request123");
		ResponseEntity result =  leaveController.requestLeave(leavePojo);
		assertNotNull(result);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		
		}
	@Test
	public void approveLeaveTest() {
		LeavePOJO leavePojo = new LeavePOJO();
		leavePojo.setEmployeeFirstName("sindhu");
		leavePojo.setEmployeeLastName("Nair");
		leavePojo.setModifiedByFirstName("Bob");
		leavePojo.setModifiedByLastName("Smith");
		leavePojo.setJsonDetails("approve request");
		ResponseEntity result =  leaveController.approveLeave(leavePojo);
		assertNotNull(result);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		
		}
	
	@Test
	public void denyLeave() {
		LeavePOJO leavePojo = new LeavePOJO();
		leavePojo.setEmployeeFirstName("Albert");
		leavePojo.setEmployeeLastName("Maine");
		leavePojo.setModifiedByFirstName("Bob");
		leavePojo.setModifiedByLastName("Smith");
		leavePojo.setJsonDetails("deny request");
		ResponseEntity result =  leaveController.approveLeave(leavePojo);
		assertNotNull(result);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		
		}


}
