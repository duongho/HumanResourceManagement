package com.ksu.grad.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ksu.grad.entity.Person;
import com.ksu.grad.entity.State;

@Transactional
@Repository
public class MiscellaneousDAOImpl implements MiscellaneousDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	private static final String SELECT_ALL_STATES = "SELECT s.* FROM EMAS.State s";

	
	@Override
	public List<State> getAllStates() {
		Query q = entityManager.createNativeQuery(SELECT_ALL_STATES, Person.class);
		List<State> allStates = q.getResultList();
		return allStates;
	}

}
