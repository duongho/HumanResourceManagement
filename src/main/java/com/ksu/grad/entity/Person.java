package com.ksu.grad.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMAS.PERSON")
public class Person implements Serializable{

	private static final long serialVersionUID = 4504281361979239496L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String username;
	
	private String password;
	
	private String emailAddress;
	
	public void setId(int id){
		this.id = id;
	}
	
	@Column(name="ID")
	public int getId(){
		return id;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	@Column(name="FirstName")
	public String getFirstName(){
		return firstName;
	}
	
	public void getLastName(String lastName){
		this.lastName = lastName;
	}
	
	@Column(name="LastName")
	public String getLastName(){
		return lastName;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	@Column(name="Password")
	public String getPassword(){
		return password;
	}

	@Column(name="EmailAddress")
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	@Column(name="UserName")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
