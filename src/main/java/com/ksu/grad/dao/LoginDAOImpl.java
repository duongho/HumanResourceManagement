package com.ksu.grad.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.ksu.grad.entity.Login;
import com.ksu.grad.service.convertors.ListConverter;

@Transactional
@Repository
public class LoginDAOImpl implements LoginDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Logger LOGGER = Logger.getLogger(LoginDAOImpl.class);
	
	private static final String GETALL = 
			"SELECT l.ID, l.UserName, l.Password, l.ResetToken, l.ValidUntil FROM EMAS.Login l";
	
	private static final String CREATE=
			"INSERT INTO EMAS.Login (UserName, Password) VALUES (?,?)";
	  
	  
	  
	

	/**
	 * get back all employees that are in the employee tables
	 */
	@Override
	public List<Login> getAllLogins() {

		Query q = entityManager.createNativeQuery(GETALL, Login.class);
		List<Login> entities = ListConverter.castList(Login.class, q.getResultList());
		 return entities;
	}
	

	/**
	 * Create a new login. 
	 * email address is combine of first name and last name
	 * password is encrypted
	 * @throws Exception 
	 */
	@Override
	public boolean create(Login p) throws Exception{
		
		boolean b = true;
		
		//TODO: Sindhu, please check for all attributes when register a new user
		if(p.getUserName() == null || p.getPassword() == null) {
			LOGGER.error("Can not create the login. UserName or Password can not be null");
			b = false;
		}
		
		//String username = emailAddress;
		
		String password = new BCryptPasswordEncoder().encode(p.getPassword());
		
		try{
			Query q = entityManager.createNativeQuery(CREATE);
			q.setParameter(1, p.getUserName());
			q.setParameter(2, password);
			
			q.executeUpdate();
		} catch (Exception e){
			LOGGER.error("Failed to register the new login. Exception is : " + e.getMessage());
			throw new Exception(e.getMessage(), e);
		}
		
		return b;
	}
}
