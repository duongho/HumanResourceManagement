package com.ksu.grad.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ksu.grad.dao.EmployeeDAO;
import com.ksu.grad.dao.MiscellaneousDAO;
import com.ksu.grad.entity.Address;
import com.ksu.grad.entity.Country;
import com.ksu.grad.entity.Employee;
import com.ksu.grad.entity.Login;
import com.ksu.grad.entity.Person;
import com.ksu.grad.entity.State;
import com.ksu.grad.pojo.EmployeePOJO;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private static final String DEFAULT_PASSWORD = "TEST";
	
	@Autowired
	private MiscellaneousDAO miscellaneousDAO;

	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}
	
	@Override
	public Employee registerEmployee(EmployeePOJO employeeModel) throws ParseException {
		Employee employee = new Employee();
		Person person = new Person();
		Address add = new Address();
		Login login = new Login();
		
		if(employeeModel.getFirstName()!=null || !employeeModel.getFirstName().equals("")) {
			person.setFirstName(employeeModel.getFirstName());
		}
		
		if(employeeModel.getLastName()!=null || !employeeModel.getLastName().equals("")) {
			person.setLastName(employeeModel.getLastName());
		}
		
		if(employeeModel.getAddress()!=null || !employeeModel.getAddress().equals("")) {
			add.setAddress(employeeModel.getAddress());
		}
		
		if(employeeModel.getCity()!=null || !employeeModel.getCity().equals("")) {
			add.setCity(employeeModel.getCity());
		}
		
		if(employeeModel.getState()!=null || !employeeModel.getState().equals("")) {
			
		}
		
		if(employeeModel.getZipcode()!=null || !employeeModel.getZipcode().equals("")) {
			add.setZipcode(employeeModel.getZipcode());
		}
		if(employeeModel.getEmailAddress()!=null || !employeeModel.getEmailAddress().equals("")) {
			person.setEmailAddress(employeeModel.getEmailAddress());
		}
		if(employeeModel.getPhone()!=null || !employeeModel.getPhone().equals("")) {
			person.setPhone(employeeModel.getPhone());
		}
			employee.setSalary(BigDecimal.valueOf(employeeModel.getSalary()));
			
			
		if(employeeModel.getStartDate()!=null) {
			employee.setStartDate(employeeModel.getStartDate());
		}
		if(employeeModel.getUserName()!=null || !employeeModel.getUserName().equals("")) {
			login.setUserName(employeeModel.getUserName());
		}
		
		
		
		List lstStates = miscellaneousDAO.getAllStates();
		State stat = null;
		if(employeeModel.getState()!=null || !employeeModel.getState().equals("")) {
		for(int i=0;i<lstStates.size();i++) {
				stat = (State) lstStates.get(i);
				if(stat.getCode().equals(employeeModel.getState())) {
						add.setState(stat);
				}
			}
		}
		
		login.setPassword(bCryptPasswordEncoder.encode(employeeModel.getPassword()));
		login.setResetToken("");
		person.setAddress(add);
		employee.setPerson(person);
		employee.setLogin(login);		
		
		return employeeDAO.registerEmplyee(employee);		
		}	
			
		
	public String generatePasswordRandomly(){
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
		String pwd = RandomStringUtils.random( 6, characters );		
		return pwd;
	}


	@Override
	public List<Employee> getAllManagers(){
		return employeeDAO.getAllManagers();
	}
	
	


	@Override
	public Employee getEmployeeById(int empId) {
		return employeeDAO.getEmployeeById(empId);
	}

	@Override
	public boolean quitEmployee(int empId) {
		return employeeDAO.quitEmployee(empId);
	}

	@Override
	public String recoverPassword(String username) {
		
		String encryptPassword = bCryptPasswordEncoder.encode(DEFAULT_PASSWORD);
		employeeDAO.updatePassword(username, encryptPassword);
		
		return DEFAULT_PASSWORD;

	}

	@Override
	public Employee updateEmployee(EmployeePOJO newEmployee) {
		
		Employee e = employeeDAO.getEmployeeByUserName(newEmployee.getUserName());
		
		if (e == null){
			return null;
		}
		
		//old address state country
		Person person = e.getPerson();
		Address address = person.getAddress();
		State state = address.getState();
		Country c = state.getCountry();
		
		if (address.getAddress().toLowerCase().trim() != newEmployee.getAddress().toLowerCase().trim()){
			address = new Address();
			address.setCity(newEmployee.getCity());
			address.setZipcode(newEmployee.getZipcode());
			address.setAddress(newEmployee.getAddress());
			
			if (state.getName() != newEmployee.getState()){
				
				state = employeeDAO.getStateByName(newEmployee.getState());
				if (state ==null){
					state = new State();
					state.setName(newEmployee.getState());
					state.setCode("31");
					state.setCountry(c); //employee can not change country. Only state and address 
				}
			}
			
			address.setState(state);
		}
		
		person.setEmailAddress(newEmployee.getEmailAddress());
		person.setPhone(newEmployee.getPhone());
		
		person.setAddress(address);
		
		e.setPerson(person);
		
		Employee updatedEmp = employeeDAO.updateEmployee(e);
		
		return updatedEmp;
	}
	
	public Employee getEmployeeByFirstandLastName(String firstName, String lastName) {
		Employee empObj = employeeDAO.getEmployeeByFirstandLastName(firstName, lastName);
		return empObj;
	}
	

}
