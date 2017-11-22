package com.ksu.grad.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.ksu.grad.dao.MiscellaneousDAO;
import com.ksu.grad.entity.State;

public class StateServiceImpl implements StateService{

	@Autowired
	MiscellaneousDAO miscellaneousDAO;
	@Override
	public List<State> getAllStates() {
		List stateList = miscellaneousDAO.getAllStates();
		return stateList;
	}
	
	
	
	

}
