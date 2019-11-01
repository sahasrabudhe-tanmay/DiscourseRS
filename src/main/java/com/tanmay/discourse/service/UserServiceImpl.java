package com.tanmay.discourse.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tanmay.discourse.model.ResponseStatus;
import com.tanmay.discourse.model.User;
import com.tanmay.discourse.model.UserResponse;
import com.tanmay.discourse.repository.UserRepository;
import com.tanmay.discourse.util.CommonResponseUtil;
import com.tanmay.discourse.util.UserResponseUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User loginUser(User user) {
		User foundUser = userRepository.findByUsername(user.getUsername());
		
		if (null != foundUser) {
			return foundUser;
		} else {
			return null;
		}
	}
	
	public ResponseEntity<UserResponse> registerUser(User user) {
		ResponseStatus responseStatus = checkUsernameAvailability(user.getUsername()).getBody();
		
		if ("SUCCESS".equals(responseStatus.getStatus())) {
			return UserResponseUtil.buildRegisterResponse(userRepository.save(user));
		} else {
			UserResponse userResponse = new UserResponse();
			userResponse.setResponseStatus(responseStatus);
			return ResponseEntity.ok(userResponse);
		}
		
	}
	
	public ResponseEntity<ResponseStatus> checkUsernameAvailability(String username) {
		User user = userRepository.findByUsername(username);
		
		if (null == user) {
			return ResponseEntity.ok(CommonResponseUtil.buildSuccessResponseStatus());
		} else {
			List<String> messages = new ArrayList<String>();
			messages.add("Username already exists");
			return ResponseEntity.ok(CommonResponseUtil.buildFailureResponseStatus(messages));
		}
	}

}
