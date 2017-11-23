package com.ksu.grad.dao;

import java.util.List;

import com.ksu.grad.entity.Country;
import com.ksu.grad.entity.State;

public interface MiscellaneousDAO {
	public List<State> getAllStates();
	
	public State getStateByName(String stateCode);
	
	public Country getCountryByName (String country);
}
