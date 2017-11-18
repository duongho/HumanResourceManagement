package com.ksu.grad.authentication;

/**
 * 
 * @author OctanePC
 *
 *This class represents the userName and password field being pass in for login authentication
 */
public class UserLogin {

	private String userName;
	private String password;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
