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

import com.ksu.grad.controller.HumanResourceController;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HumanResourceControllerTest {
	@Autowired
	private  HumanResourceController humanResourceController;
	
	@Test
	public void terminateEmployeeByHRTest() {
		ResponseEntity result =  humanResourceController.terminateEmployeeByHR(11);
		assertNotNull(result);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
	}
	@Test
	public void getAllManagersTest() {
		ResponseEntity result =  humanResourceController.getAllMAnagers();
		assertNotNull(result);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
	}
}
