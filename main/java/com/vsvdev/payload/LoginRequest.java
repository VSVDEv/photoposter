package com.vsvdev.payload;

import javax.validation.constraints.NotEmpty;

public class LoginRequest {
	@NotEmpty(message = "Username can't be empty")
	private String userName;
	@NotEmpty(message = "Password can't be empty")
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

	public LoginRequest(String userName, String password) {

		this.userName = userName;
		this.password = password;
	}

	public LoginRequest() {

	}

}
