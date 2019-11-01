package com.tanmay.discourse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanmay.discourse.model.User;
import com.tanmay.discourse.repository.UserRepository;

@Service
public class UserService {
	
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

}
