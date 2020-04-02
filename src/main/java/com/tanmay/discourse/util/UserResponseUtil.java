package com.tanmay.discourse.util;

import org.springframework.http.ResponseEntity;

import com.tanmay.discourse.model.User;
import com.tanmay.discourse.model.UserResponse;

public class UserResponseUtil {

	public static ResponseEntity<UserResponse> buildUserResponse(User loginUser, User user, String token) {
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
			userResponse.setToken(token);

			return ResponseEntity.ok(userResponse);
		}
	}
	
	public static ResponseEntity<UserResponse> buildRegisterResponse(User user, String token) {
		UserResponse userResponse = new UserResponse();
		
		if (null == user) {
			userResponse.getResponseStatus().setStatus("FAILURE");
			userResponse.getResponseStatus().getMessages().add("Something went wrong while saving");
			userResponse.setUser(null);

			return ResponseEntity.ok(userResponse);
		} else {
			userResponse.getResponseStatus().setStatus("SUCCESS");
			userResponse.setUser(user);
			userResponse.setToken(token);

			return ResponseEntity.ok(userResponse);
		}
	}

}
