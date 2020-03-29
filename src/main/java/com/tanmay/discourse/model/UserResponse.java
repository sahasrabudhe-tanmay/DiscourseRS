package com.tanmay.discourse.model;

public class UserResponse {
	
	private ResponseStatus responseStatus;
	private User user;
	private String token;
	
	public UserResponse() {
		super();
		this.responseStatus = new ResponseStatus();
		this.user = new User();
	}
	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
