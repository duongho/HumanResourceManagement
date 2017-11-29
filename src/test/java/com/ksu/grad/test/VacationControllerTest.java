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

import com.ksu.grad.controller.VacationController;
import com.ksu.grad.pojo.VacationPOJO;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VacationControllerTest {
	
	@Autowired
	private VacationController vacationController;
	/*@Test
	public void requestVacationTest() {
		VacationPOJO vacationPojo = new VacationPOJO();
		vacationPojo.setEmployeeFirstName("Duong");
		vacationPojo.setEmployeeLastName("Ho");
		vacationPojo.setModifiedByFirstName("Bob");
		vacationPojo.setModifiedByLastName("Smith");
		vacationPojo.setJsonDetails("vacation create junit3");
		ResponseEntity result = vacationController.requestVacation(vacationPojo);
		assertNotNull(result);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
	}*/
	
	/*@Test
	public void approveVacationtest() {
		VacationPOJO vacationPojo = new VacationPOJO();
		vacationPojo.setEmployeeFirstName("Sindhu");
		vacationPojo.setEmployeeLastName("Nair");
		vacationPojo.setModifiedByFirstName("Bob");
		vacationPojo.setModifiedByLastName("Smith");
		vacationPojo.setJsonDetails("approve create junit1");
		ResponseEntity result = vacationController.approveVacation(vacationPojo);
		assertNotNull(result);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
	}*/

	/*@Test
	public void denyVacationTest() {
		VacationPOJO vacationPojo = new VacationPOJO();
		vacationPojo.setEmployeeFirstName("Duong");
		vacationPojo.setEmployeeLastName("Ho");
		vacationPojo.setModifiedByFirstName("Bob");
		vacationPojo.setModifiedByLastName("Smith");
		vacationPojo.setJsonDetails("vacation create junit3");
		ResponseEntity result = vacationController.denyVacation(vacationPojo);
		assertNotNull(result);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
	}*/
	
	@Test
	public void etAllPendingVacationRequestForEmployeeTest() {
		ResponseEntity result = vacationController.getAllPendingVacationRequestForEmployee(1);
		assertNotNull(result);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
	}
}
