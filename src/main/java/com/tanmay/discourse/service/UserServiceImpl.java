package com.tanmay.discourse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tanmay.discourse.model.User;
import com.tanmay.discourse.model.UserResponse;
import com.tanmay.discourse.repository.UserRepository;
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
		User registeredUser = userRepository.save(user);
		
		return UserResponseUtil.buildRegisterResponse(registeredUser);
	}

}
