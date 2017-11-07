package com.ksu.grad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ksu.grad.entity.Person;
import com.ksu.grad.service.PersonService;

@Controller
@RequestMapping("employee")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	
	@GetMapping("all")
	public ResponseEntity<List<Person>> getAllEmployees() {
		List<Person> list = personService.getAllEmployees();
		
		return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
	}
	
	@GetMapping("hello")
	public ResponseEntity<String> sayHello(){
		return new ResponseEntity<String>("Hello", HttpStatus.OK);
	}
}
