package com.ksu.grad.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ksu.grad.entity.Person;

@Transactional
@Repository
public class PersonDAOImpl implements PersonDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Person> getAllEmployees() {
		String sql = "SELECT a.id, a.firstname, a.lastname, a.username FROM EMAS.PERSON a";
	
		Query q = entityManager.createNativeQuery(sql, Person.class);
		List<Person> employees = q.getResultList();
		 return employees;
	}

}
