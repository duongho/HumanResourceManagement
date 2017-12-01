package com.ksu.grad.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ksu.grad.controller.EmployeeController;
import com.ksu.grad.entity.Employee;
import com.ksu.grad.pojo.EmployeePOJO;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeControllerTest {
	
	@Autowired
	private EmployeeController employeeController;
	
	@Test
	public void getAllEmployeeTest() {
		ResponseEntity result =  employeeController.getAllEmployees();
		assertNotNull(result);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
	}
	/*@Test
	public void registerTest() throws Exception {
		EmployeePOJO employeePojo = new EmployeePOJO();
		employeePojo.setAddress("123 Straight Road");
		employeePojo.setCity("West");
		employeePojo.setCountry("USA");
		employeePojo.setState("MI");
		employeePojo.setEmailAddress("skomalan@students.ksu.edu");
		employeePojo.setFirstName("Betty");
		employeePojo.setLastName("Steve");
		employeePojo.setPassword("TEST");
		employeePojo.setPhone("1456237890");
		employeePojo.setSalary(300000.00);
		Date dateobj = new Date();
		employeePojo.setStartDate(dateobj);
		employeePojo.setUserName("betty");
		employeePojo.setZipcode("48084");
		ResponseEntity result =  employeeController.register(employeePojo);
		assertNotNull(result);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		//assertEquals(employeePojo,result.getClass());
		
	}*/
	@Test
	public void quitEmployeeTest() {
		ResponseEntity result =  employeeController.terminateEmployee(12);
		assertNotNull(result);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		
	}
	
	@Test
	public void updatePassword() {
		ResponseEntity result =  employeeController.recoverPassword("rose");
		assertNotNull(result);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void updateEmployee(){
		EmployeePOJO employeeModel = new EmployeePOJO();
		employeeModel.setAddress("4217 Birchwood Way");
		employeeModel.setCity("Marietta");
		employeeModel.setCountry("United States");
		employeeModel.setEmailAddress("duonghose2gmail@yahoo.com");
		employeeModel.setUserName("duong");
		employeeModel.setPhone("123-555-1111");
		employeeModel.setState("MyState");
		employeeModel.setZipcode("12323");
		
		ResponseEntity<Employee> e = employeeController.updateProfile(employeeModel);
		assertNotNull(e);
	}
}


