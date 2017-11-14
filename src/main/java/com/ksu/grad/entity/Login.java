package com.ksu.grad.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMAS.Login")
public class Login implements Serializable{

	private static final long serialVersionUID = 4504281361979239496L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;
	
	private String userName;
	
	private String password;
	
	private String resetToken;
	
	private String validUntil;
	
	public void setId(int id){
		this.id = id;
	}
	
	@Column(name="ID")
	public int getId(){
		return id;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	@Column(name="UserName")
	public String getUserName(){
		return this.userName;
	}
	
	public void getPassword(String password){
		this.password = password;
	}
	
	@Column(name="Password")
	public String getPassword(){
		return this.password;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	@Column(name="ResetToken")
	public String getResetToken() {
		return this.resetToken;
	}

	public void setValidUntil(String validUntil) {
		this.validUntil = validUntil;
	}

	@Column(name="ValidUntil")
	public String getValidUntil() {
		return this.validUntil;
	}
}
