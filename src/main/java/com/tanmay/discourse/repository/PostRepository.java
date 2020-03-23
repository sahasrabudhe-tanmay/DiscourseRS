package com.tanmay.discourse.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tanmay.discourse.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {
	
	public List<Post> findAllPostsByPostedBy(String username);

}
