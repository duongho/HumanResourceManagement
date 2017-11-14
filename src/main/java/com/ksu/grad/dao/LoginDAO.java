package com.ksu.grad.dao;

import java.util.List;

import com.ksu.grad.entity.Login;

public interface LoginDAO {

	List<Login> getAllLogins();
	
	//return success/failure
	boolean create(Login p) throws Exception;
		
}
