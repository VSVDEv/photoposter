package com.vsvdev.payload;

public class JWTTokenSuccessResponse {
private boolean success;
private String token;
public JWTTokenSuccessResponse() {
	}
public JWTTokenSuccessResponse(boolean success, String token) {
	this.success = success;
	this.token = token;
}
public boolean isSuccess() {
	return success;
}
public void setSuccess(boolean success) {
	this.success = success;
}
public String getToken() {
	return token;
}
public void setToken(String token) {
	this.token = token;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (success ? 1231 : 1237);
	result = prime * result + ((token == null) ? 0 : token.hashCode());
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
	JWTTokenSuccessResponse other = (JWTTokenSuccessResponse) obj;
	if (success != other.success)
		return false;
	if (token == null) {
		if (other.token != null)
			return false;
	} else if (!token.equals(other.token))
		return false;
	return true;
}
@Override
public String toString() {
	return "JWTTokenSuccessResponse [success=" + success + ", token=" + token + "]";
}

}
