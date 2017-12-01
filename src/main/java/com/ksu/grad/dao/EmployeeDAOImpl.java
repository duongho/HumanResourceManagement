package com.ksu.grad.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.ksu.grad.entity.Employee;
import com.ksu.grad.entity.Login;
import com.ksu.grad.entity.State;

@Transactional
@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Logger LOGGER = Logger.getLogger(EmployeeDAOImpl.class);
	
	private static final String SELECT_ALL_EMPLOYEES = 
			"SELECT a.* FROM EMAS.Employee a";
	
	private static final String SELECT_EMPLOYEE_PROFILE_FOR_EMP_ID=
			"SELECT a.* FROM EMAS.Employee a WHERE a.ID = :empId";
	
	private static final String SELECT_STATE_BY_NAME  = "SELECT a.* FROM EMAS.State a WHERE a.Name= :name";


	private static final String SELECT_ALL_MANAGERS = "SELECT DISTINCT a.* FROM EMAS.Employee a INNER JOIN EMAS.EmployeeCorrelation b \n" + 
			"ON a.ID = b.ManagerId ";
	
	private static final String QUIT_EMPLOYEE ="UPDATE Employee e set e.isActive=0 where e.id= :id";
	
	private static final String UPDATE_PASSWORD= "UPDATE Login a SET a.Password= :password WHERE a.UserName= :username";
	
	private static final String SELECT_EMPLOYEE_BY_USERNAME = "SELECT e.* FROM EMAS.Employee e INNER JOIN EMAS.Login a ON a.ID = e.PersonId WHERE a.UserName = :userName";
	
	@Override
	public List<Employee> getAllEmployees() {

		Query q = entityManager.createNativeQuery(SELECT_ALL_EMPLOYEES, Employee.class);
		List<Employee> allEmployees = q.getResultList();		
		 return allEmployees;
	}

	@Override
	public Employee getEmployeeById(int empId) {
		Query q = entityManager.createNativeQuery(SELECT_EMPLOYEE_PROFILE_FOR_EMP_ID, Employee.class);
		q.setParameter("empId", empId);
		
		List<Employee> employees = q.getResultList();
		
		if (employees == null || employees.isEmpty()){
			return null;
		}
		
		return employees.get(0);
	}

	public Employee registerEmplyee(Employee employee) {
		entityManager.persist(employee);
		return employee;
	}


	@Override
	public Employee updateEmployee(Employee employee) {
		Employee e = entityManager.merge(employee);
		
		return e;
	}

	@Override
	public List<Employee> getAllManagers() {		
		Query q = entityManager.createNativeQuery(SELECT_ALL_MANAGERS, Employee.class);
		List<Employee> managerLst = q.getResultList();
		return managerLst;
	}

	@Override
	public boolean quitEmployee(int empId) {
		Query q = entityManager.createNativeQuery(QUIT_EMPLOYEE, Employee.class);
		q.setParameter("id", empId);
		int status = q.executeUpdate();
		if (status==1) {
			return true;
		}else {
			return false;
		}
			
	}

	/**
	 * return true if no exception occurs
	 */
	@Override
	public boolean updatePassword(String username, String encryptPassword) {
		
		boolean b = false;
		Query q = entityManager.createNativeQuery(UPDATE_PASSWORD, Login.class);
		q.setParameter("username", username);
		q.setParameter("password", encryptPassword);
		
		q.executeUpdate();
		b=true;
		
		return b;
	}

	@Override
	public Employee getEmployeeByUserName(String userName) {
		
		Query q = entityManager.createNativeQuery(SELECT_EMPLOYEE_BY_USERNAME, Employee.class);
		q.setParameter("userName", userName);
		
		List<Employee> empList = q.getResultList();
		if (empList == null || empList.isEmpty()){
			return null;
		}
		
		return empList.get(0);
	}

	@Override
	public State getStateByName(String name) {
		
		Query q = entityManager.createNativeQuery(SELECT_STATE_BY_NAME, State.class);
		q.setParameter("name", name);
		
		List<State> states = q.getResultList();
		if (states == null || states.isEmpty()){
			return null;
		}
		
		return states.get(0);
	}
}
