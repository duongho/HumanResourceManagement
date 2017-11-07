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
	
	private String userName;
	
	private String password;
	
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
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	@Column(name="UserName")
	public String getUserName(){
		return userName;
	}

	public void setPassword(String password){
		this.password = password;
	}
	
	@Column(name="Password")
	public String getPassword(){
		return password;
	}
}
