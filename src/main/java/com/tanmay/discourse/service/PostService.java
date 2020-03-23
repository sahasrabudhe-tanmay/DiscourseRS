package com.tanmay.discourse.service;

import org.springframework.http.ResponseEntity;

import com.tanmay.discourse.model.Post;
import com.tanmay.discourse.model.PostResponse;

public interface PostService {
	
	public ResponseEntity<PostResponse> createPost(Post post);
	public ResponseEntity<PostResponse> findPostById(String id);
	public ResponseEntity<PostResponse> findAllPostsByUsername(String username);
	public ResponseEntity<PostResponse> likePost(String id, String username);
	public ResponseEntity<PostResponse> dislikePost(String id, String username);

}
