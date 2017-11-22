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
import com.ksu.grad.entity.Person;

@Transactional
@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Logger LOGGER = Logger.getLogger(EmployeeDAOImpl.class);
	
	private static final String SELECT_ALL_EMPLOYEES = 
			"SELECT a.* FROM EMAS.Employee a";
	private static final String SELECT_EMPLOYEE_BY_ID = "SELECT a.* FROM EMAS.Employee a where a.id =?";

	private static final String SELECT_ALL_MANAGERS = "SELECT DISTINCT a.* FROM EMAS.Employee a INNER JOIN EMAS.EmployeeCorrelation b \n" + 
			"ON a.ID = b.ManagerId ";
	
	@Override
	public List<Employee> getAllEmployees() {

		Query q = entityManager.createNativeQuery(SELECT_ALL_EMPLOYEES, Employee.class);
		List<Employee> allEmployees = q.getResultList();		
		 return allEmployees;
	}

	@Override
	public Employee registerEmplyee(Employee employee) {
		entityManager.persist(employee);
		return employee;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Query q = entityManager.createNativeQuery(SELECT_EMPLOYEE_BY_ID, Employee.class);
		q.setParameter(0,id);
		Employee employee = (Employee) q.getResultList().get(0);
		return employee;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Employee.class,"employeeClass");
		criteria.add(Restrictions.eq("employeeClass.id",employee.getId()));
		session.saveOrUpdate(employee);
		return employee;
	}

	@Override
	public List<Employee> getAllManagers() {		
		Query q = entityManager.createNativeQuery(SELECT_ALL_MANAGERS, Employee.class);
		List<Employee> managerLst = q.getResultList();
		return managerLst;
	}
	
	

}
