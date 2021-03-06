package com.tanmay.discourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanmay.discourse.model.Post;
import com.tanmay.discourse.model.PostResponse;
import com.tanmay.discourse.service.PostService;

@CrossOrigin
@RestController
@RequestMapping(path = "/discourse-rs/post")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostResponse> createPost(@RequestBody Post post) {
		return postService.createPost(post);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostResponse> findPostById(@PathVariable String id) {
		return postService.findPostById(id);
	}
	
	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostResponse> findAllPosts() {
		return postService.findAllPosts();
	}
	
	@GetMapping(path = "/user/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostResponse> findAllPostsByUsername(@PathVariable String username) {
		return postService.findAllPostsByUsername(username);
	}
	
	@GetMapping(path = "/like/id/{id}/user/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostResponse> likePost(@PathVariable String id, @PathVariable String username) {
		return postService.likePost(id, username);
	}
	
	@GetMapping(path = "/dislike/id/{id}/user/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostResponse> dislikePost(@PathVariable String id, @PathVariable String username) {
		return postService.dislikePost(id, username);
	}
	
	@GetMapping(path = "/clearLikes/id/{id}/user/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostResponse> clearLikesForPost(@PathVariable String id, @PathVariable String username) {
		return postService.clearLikesForPost(id, username);
	}

}
