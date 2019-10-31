package com.tanmay.discourse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tanmay.discourse.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	public User findByUsername(String username);
}
