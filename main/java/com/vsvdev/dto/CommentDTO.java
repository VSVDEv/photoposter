package com.vsvdev.dto;

import javax.validation.constraints.NotEmpty;

public class CommentDTO {
private Long id;
@NotEmpty
private String message;
private String userName;
public CommentDTO(Long id, @NotEmpty String message, String userName) {
	this.id = id;
	this.message = message;
	this.userName = userName;
}
public CommentDTO() {
	}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((message == null) ? 0 : message.hashCode());
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
	CommentDTO other = (CommentDTO) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	if (message == null) {
		if (other.message != null)
			return false;
	} else if (!message.equals(other.message))
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
	return "CommentDTO [id=" + id + ", message=" + message + ", userName=" + userName + "]";
}

}
