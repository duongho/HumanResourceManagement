package com.ksu.grad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ksu.grad.dao.PersonDAO;
import com.ksu.grad.entity.Person;

@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonDAO personDAO;

	@Override
	public List<Person> getAllEmployees() {
		return personDAO.getAllEmployees();
	}

	@Override
	public boolean registerNewEmployee(Person p) throws Exception {
		return personDAO.registerNewEmployee(p);
	}

}
