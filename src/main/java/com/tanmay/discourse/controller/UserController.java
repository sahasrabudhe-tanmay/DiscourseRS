package com.tanmay.discourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanmay.discourse.model.User;
import com.tanmay.discourse.model.UserResponse;
import com.tanmay.discourse.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/discourse-rs/user")
public class UserController {
	
	@Autowired
	UserService discourseService;

	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginUser(@RequestBody User user) {
		User loginUser = discourseService.loginUser(user);
		
		return buildUserResponse(loginUser, user);
	}

	private ResponseEntity<UserResponse> buildUserResponse(User loginUser, User user) {
		UserResponse userResponse = new UserResponse();
		
		if (null == loginUser) {
			userResponse.getResponseStatus().setStatus("FAILURE");
			userResponse.getResponseStatus().getMessages().add("Username does not exist");
			userResponse.setUser(null);
			
			return ResponseEntity.ok(userResponse);
		} else if (!user.getPassword().equals(loginUser.getPassword())) {
			userResponse.getResponseStatus().setStatus("FAILURE");
			userResponse.getResponseStatus().getMessages().add("Passwords don't match");
			userResponse.setUser(null);
			
			return ResponseEntity.ok(userResponse);
		} else {
			userResponse.getResponseStatus().setStatus("SUCCESS");
			userResponse.setUser(loginUser);
			
			return ResponseEntity.ok(userResponse);
		}
	}

}
