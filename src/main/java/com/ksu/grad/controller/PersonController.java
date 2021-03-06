package com.ksu.grad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ksu.grad.entity.Person;
import com.ksu.grad.service.PersonService;

@Controller
@RequestMapping("api/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	
	@GetMapping("all")
	public ResponseEntity<List<Person>> getAllPerson() {
		List<Person> list = personService.getAllPersons();
		
		return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
	}
	
}
