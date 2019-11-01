package com.tanmay.discourse.service;

import org.springframework.http.ResponseEntity;

import com.tanmay.discourse.model.User;
import com.tanmay.discourse.model.UserResponse;

public interface UserService {
	
	public User loginUser(User user);
	
	public ResponseEntity<UserResponse> registerUser(User user);

}
