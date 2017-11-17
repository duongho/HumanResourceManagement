package com.ksu.grad.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ksu.grad.entity.Person;
import com.ksu.grad.service.convertors.ListConverter;

@Transactional
@Repository
public class PersonDAOImpl implements PersonDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Logger LOGGER = Logger.getLogger(PersonDAOImpl.class);
	
	private static final String SELECT_ALL_EMPLOYEES = 
			"SELECT a.* FROM EMAS.Person a";
	
	private static final String REGISTER_NEW_USER=
			"INSERT INTO EMAS.Person (FirstName, LastName, EmailAddress, Phone) VALUES (?,?,?,?)";
	  
	  
	  
	
	
	/**
	 * get back all employees that are in the employee tables
	 */
	@Override
	public List<Person> getAllEmployees() {

		Query q = entityManager.createNativeQuery(SELECT_ALL_EMPLOYEES, Person.class);
		List<Person> employees = ListConverter.castList(Person.class, q.getResultList());
		 return employees;
	}
	

	/**
	 * Register a new user. 
	 * email address is combine of first name and last name
	 * password is encrypted
	 * @throws Exception 
	 */
	@Override
	public boolean registerNewEmployee(Person p) throws Exception{
		
		boolean b = true;
		
		//TODO: Sindhu, please check for all attributes when register a new user
		if(p.getFirstName() == null || p.getLastName() == null) {
			LOGGER.error("Can not register a new employee. First name or last name must not be null");
			b = false;
		}
		
		//auto generate email
		String emailAddress = p.getFirstName() + "_" + p.getLastName() + "@ksu.edu";
		
		//in a corporate office the email address is really the username
		//String username = emailAddress;
		
		//String password = new BCryptPasswordEncoder().encode(p.getPassword());
		
		try{
			Query q = entityManager.createNativeQuery(REGISTER_NEW_USER);
			q.setParameter(1, p.getFirstName());
			q.setParameter(2, p.getLastName());
			q.setParameter(3, emailAddress);
			q.setParameter(4, p.getPhone());
			
			q.executeUpdate();
		} catch (Exception e){
			LOGGER.error("Failed to register the new employee. Exception is : " + e.getMessage());
			throw new Exception(e.getMessage(), e);
		}
		
		return b;
	}
}
