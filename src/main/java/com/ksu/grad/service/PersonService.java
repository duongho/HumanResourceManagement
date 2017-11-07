package com.ksu.grad.service;

import java.util.List;

import com.ksu.grad.entity.Person;

public interface PersonService {
	
	List<Person> getAllEmployees();
	
	boolean registerNewEmployee(Person p) throws Exception;
}
