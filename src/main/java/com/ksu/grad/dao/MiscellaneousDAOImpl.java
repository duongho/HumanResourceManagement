package com.ksu.grad.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ksu.grad.entity.Country;
import com.ksu.grad.entity.Person;
import com.ksu.grad.entity.State;

@Transactional
@Repository
public class MiscellaneousDAOImpl implements MiscellaneousDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	private static final String SELECT_ALL_STATES = "SELECT s.* FROM EMAS.State s";
	private static final String SELECT_STATE_BY_STATE_NAME = "SELECT s FROM EMAS.State s where s.Name= :stateName";
	private static final String SELECT_COUNTRY_BY_NAME = "SELECT s FROM EMAS.Country s where s.Name= :countryName";

	
	@Override
	public List<State> getAllStates() {
		Query q = entityManager.createNativeQuery(SELECT_ALL_STATES, State.class);
		List<State> allStates = q.getResultList();
		return allStates;
	}
	
	@Override
	public State getStateByName(String stateName){
		Query q = entityManager.createNativeQuery(SELECT_STATE_BY_STATE_NAME, State.class);
		q.setParameter("stateName", stateName);
		List<State> states = q.getResultList();
		if (states ==null){
			return null;
		}
		
		return states.get(0);
	}
	
	@Override
	public Country getCountryByName (String countryName){
		Query q = entityManager.createNativeQuery(SELECT_COUNTRY_BY_NAME, Country.class);
		q.setParameter("countryName", countryName);
		List<Country> countries = q.getResultList();
		if (countries ==null){
			return null;
		}
		
		return countries.get(0);
	}
	

	
	

}
