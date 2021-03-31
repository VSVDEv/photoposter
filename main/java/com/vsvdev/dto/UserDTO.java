package com.vsvdev.dto;

import javax.validation.constraints.NotEmpty;

public class UserDTO {
private Long id;
@NotEmpty
private String firstName;
@NotEmpty
private String lastName;

private String userName;
private String biography;
public UserDTO(Long id, @NotEmpty String firstName, @NotEmpty String lastName, @NotEmpty String userName,
		String biography) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.userName = userName;
	this.biography = biography;
}
public UserDTO() {
	}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
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
public String getBiography() {
	return biography;
}
public void setBiography(String biography) {
	this.biography = biography;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((biography == null) ? 0 : biography.hashCode());
	result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
	UserDTO other = (UserDTO) obj;
	if (biography == null) {
		if (other.biography != null)
			return false;
	} else if (!biography.equals(other.biography))
		return false;
	if (firstName == null) {
		if (other.firstName != null)
			return false;
	} else if (!firstName.equals(other.firstName))
		return false;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	if (lastName == null) {
		if (other.lastName != null)
			return false;
	} else if (!lastName.equals(other.lastName))
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
	return "UserDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
			+ ", biography=" + biography + "]";
}


}
