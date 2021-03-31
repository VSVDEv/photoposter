package com.vsvdev.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.vsvdev.annotations.PasswordMatches;
import com.vsvdev.annotations.ValidEmail;
@PasswordMatches
public class SignUpRequest {
	@Email(message = "Required @email format")
	@NotBlank(message = "Required email")
	@ValidEmail
private String email;
	@NotEmpty(message = "Please enter first name")
private String firstName;
	@NotEmpty(message = "Please enter last name")
private String lastName;
	@NotEmpty(message = "Please enter user name")
private String userName;
	@NotEmpty(message = "Required password")
	@Size(min = 6)
private String password;
private String confirmPassword;

public SignUpRequest() {
	
}

public SignUpRequest(@Email(message = "Required @email format") @NotBlank(message = "Required email") String email,
		@NotEmpty(message = "Please enter first name") String firstName,
		@NotEmpty(message = "Please enter last name") String lastName,
		@NotEmpty(message = "Please enter user name") String userName,
		@NotEmpty(message = "Required password") @Size(min = 6) String password, String confirmPassword) {
	this.email = email;
	this.firstName = firstName;
	this.lastName = lastName;
	this.userName = userName;
	this.password = password;
	this.confirmPassword = confirmPassword;
}

public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
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
public String getConfirmPassword() {
	return confirmPassword;
}
public void setConfirmPassword(String confirmPassword) {
	this.confirmPassword = confirmPassword;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((confirmPassword == null) ? 0 : confirmPassword.hashCode());
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
	result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
	result = prime * result + ((password == null) ? 0 : password.hashCode());
	result = prime * result + ((userName == null) ? 0 : userName.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	SignUpRequest other = (SignUpRequest) obj;
	if (confirmPassword == null) {
		if (other.confirmPassword != null)
			return false;
	} else if (!confirmPassword.equals(other.confirmPassword))
		return false;
	if (email == null) {
		if (other.email != null)
			return false;
	} else if (!email.equals(other.email))
		return false;
	if (firstName == null) {
		if (other.firstName != null)
			return false;
	} else if (!firstName.equals(other.firstName))
		return false;
	if (lastName == null) {
		if (other.lastName != null)
			return false;
	} else if (!lastName.equals(other.lastName))
		return false;
	if (password == null) {
		if (other.password != null)
			return false;
	} else if (!password.equals(other.password))
		return false;
	if (userName == null) {
		if (other.userName != null)
			return false;
	} else if (!userName.equals(other.userName))
		return false;
	return true;
}
@Override
public String toString() {
	return "SignUpRequest [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
			+ userName + ", password=" + password + ", confirmPassword=" + confirmPassword + "]";
}


}
