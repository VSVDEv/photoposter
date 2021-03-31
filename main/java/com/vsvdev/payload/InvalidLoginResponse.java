package com.vsvdev.payload;

public class InvalidLoginResponse {
private String userName;
private String password;

public InvalidLoginResponse() {
	this.userName = "Invalid username";
	this.password = "Invalid password";
}

public String getUserName() {
	return userName;
}

public String getPassword() {
	return password;
}


}
