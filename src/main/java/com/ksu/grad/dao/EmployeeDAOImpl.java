package com.ksu.grad.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.ksu.grad.entity.Employee;
import com.ksu.grad.entity.Person;

@Transactional
@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Logger LOGGER = Logger.getLogger(EmployeeDAOImpl.class);
	
	private static final String SELECT_ALL_EMPLOYEES = 
			"SELECT a.* FROM EMAS.Employee a";

	@Override
	public List<Employee> getAllEmployees() {

		Query q = entityManager.createNativeQuery(SELECT_ALL_EMPLOYEES, Employee.class);
		List<Employee> allEmployees = q.getResultList();
		
		 return allEmployees;
	}

}
