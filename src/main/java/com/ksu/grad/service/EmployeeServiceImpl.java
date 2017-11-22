package com.ksu.grad.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ksu.grad.dao.EmployeeDAO;
import com.ksu.grad.dao.MiscellaneousDAO;
import com.ksu.grad.entity.Address;
import com.ksu.grad.entity.Employee;
import com.ksu.grad.entity.Login;
import com.ksu.grad.entity.Person;
import com.ksu.grad.entity.State;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
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
	public Employee registerEmployee(String firstname, String lastname, String address, 
    		String email, String phone,String salary, String startDate,String username, String city, String statecode, String zipcode) throws ParseException {
		Employee employee = new Employee();
		Person person = new Person();
		Address add = new Address();
		Login login = new Login();
		DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
		Date date = format.parse(startDate);
		if(firstname!=null || !firstname.equals("")) {
			person.setFirstName(firstname);
		}if(lastname!=null || !lastname.equals("")) {
			person.setLastName(lastname);
		}
		if(address!=null || !address.equals("")) {
			add.setAddress(address);
		}if(city!=null || !city.equals("")) {
			add.setCity(city);
		}if(statecode!=null || !statecode.equals("")) {
			
		}if(zipcode!=null || !zipcode.equals("")) {
			add.setZipcode(zipcode);
		}
		if(email!=null || !email.equals("")) {
			person.setEmailAddress(email);
		}
		if(phone!=null || !phone.equals("")) {
			person.setPhone(phone);
		}
		if(salary!=null || !salary.equals("")) {			
			employee.setSalary(new BigDecimal(salary));
		}
		if(startDate!=null || !startDate.equals("")) {
			employee.setStartDate(date);
		}
		if(username!=null || !username.equals("")) {
			login.setUserName(username);
		}
		
		List lstStates = miscellaneousDAO.getAllStates();
		State stat = null;
		if(statecode!=null || statecode.equals("")) {
		for(int i=0;i<lstStates.size();i++) {
			stat = (State) lstStates.get(i);
			if(stat.getCode().equals(statecode)) {
				add.setState(stat);
			}
		}
		}
		login.setPassword(bCryptPasswordEncoder.encode(generatePasswordRandomly()));
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
	public Employee updateProfile(String firstname, String lastname, String address, String email, String phone,
			String salary, String startDate, String username, String city, String statecode, String zipcode,
			String password, int id) throws ParseException {
		Employee employee = employeeDAO.getEmployeeById(id);
		Employee updatedEmployee = null;
		if(employee!=null) {
			DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
			Date date = format.parse(startDate);
			if(firstname!=null || !firstname.equals("")) {
				employee.getPerson().setFirstName(firstname);
			}if(lastname!=null || !lastname.equals("")) {
				employee.getPerson().setLastName(lastname);
			}
			if(address!=null || !address.equals("")) {
				employee.getPerson().getAddress().setAddress(address);
			}if(city!=null || !city.equals("")) {
				employee.getPerson().getAddress().setCity(city);
			}if(statecode!=null || !statecode.equals("")) {
				
			}if(zipcode!=null || !zipcode.equals("")) {
				employee.getPerson().getAddress().setZipcode(zipcode);
			}
			if(email!=null || !email.equals("")) {
				employee.getPerson().setEmailAddress(email);
			}
			if(phone!=null || !phone.equals("")) {
				employee.getPerson().setPhone(phone);
			}
			if(salary!=null || !salary.equals("")) {			
				employee.setSalary(new BigDecimal(salary));
			}
			if(startDate!=null || !startDate.equals("")) {
				employee.setStartDate(date);
			}
			if(username!=null || !username.equals("")) {
				employee.getLogin().setUserName(username);
			}if(password!=null || !password.equals("")) {
				employee.getLogin().setPassword(password);
			}
			
			List<State> lstStates = miscellaneousDAO.getAllStates();
			State stat = null;
			if(statecode!=null || statecode.equals("")) {
			for(int i=0;i<lstStates.size();i++) {
				stat = (State) lstStates.get(i);
				if(stat.getCode().equals(statecode)) {
					employee.getPerson().getAddress().setState(stat);
				}
			}
		}
			
			updatedEmployee = employeeDAO.updateEmployee(employee);
		}
		return updatedEmployee;
	}

	public Employee getEmployeebyId(int id) {
		return employeeDAO.getEmployeeById(id);
	}

	@Override
	public List<Employee> getAllManagers(){
		return employeeDAO.getAllManagers();
	}
	
	

}
