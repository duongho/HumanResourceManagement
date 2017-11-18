package com.ksu.grad.dao;

import java.util.List;

import com.ksu.grad.entity.Person;

public interface PersonDAO {

	List<Person> getAllPersons();
	
	//return success/failure
	boolean registerNewEmployee(Person p) throws Exception;
		
}
