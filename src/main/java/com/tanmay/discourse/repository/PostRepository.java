package com.tanmay.discourse.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.tanmay.discourse.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {
	
//	public List<Post> findAllPostsByUsername(String username);

}
