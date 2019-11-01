package com.tanmay.discourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanmay.discourse.model.ResponseStatus;
import com.tanmay.discourse.model.User;
import com.tanmay.discourse.model.UserResponse;
import com.tanmay.discourse.service.UserService;
import com.tanmay.discourse.util.UserResponseUtil;

@CrossOrigin
@RestController
@RequestMapping("/discourse-rs/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginUser(@RequestBody User user) {
		User loginUser = userService.loginUser(user);
		
		return UserResponseUtil.buildUserResponse(loginUser, user);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserResponse> registerUser(@RequestBody User user) {
		return userService.registerUser(user);
	}
	
	@GetMapping("/check-availability/{username}")
	public ResponseEntity<ResponseStatus> checkUsernameAvailability(@PathVariable String username) {
		return userService.checkUsernameAvailability(username);
	}
	
}
