package com.ksu.grad.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMAS.Person")
public class Person implements Serializable{

	private static final long serialVersionUID = 4504281361979239496L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;
	
	private String FirstName;
	
	private String LastName;
	
	private String EmailAddress;
	
	private String Phone;
	
	public void setId(int id){
		this.id = id;
	}
	
	@Column(name="ID")
	public int getId(){
		return id;
	}
	
	public void setFirstName(String firstName){
		this.FirstName = firstName;
	}
	
	@Column(name="FirstName")
	public String getFirstName(){
		return this.FirstName;
	}
	
	public void getLastName(String lastName){
		this.LastName = lastName;
	}
	
	@Column(name="LastName")
	public String getLastName(){
		return this.LastName;
	}

	public void setEmailAddress(String emailAddress) {
		this.EmailAddress = emailAddress;
	}

	@Column(name="EmailAddress")
	public String getEmailAddress() {
		return this.EmailAddress;
	}

	@Column(name="Phone")
	public String getPhone() {
		return this.Phone;
	}

	public void setPhone(String phone) {
		this.Phone = phone;
	}
}
