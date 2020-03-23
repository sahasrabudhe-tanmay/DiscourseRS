package com.tanmay.discourse.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tanmay.discourse.model.Post;
import com.tanmay.discourse.model.PostResponse;
import com.tanmay.discourse.model.User;
import com.tanmay.discourse.repository.PostRepository;
import com.tanmay.discourse.repository.UserRepository;
import com.tanmay.discourse.util.CommonResponseUtil;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public ResponseEntity<PostResponse> createPost(Post post) {
		PostResponse postResponse = new PostResponse();
		post.setLikes(new BigDecimal(0));
		post.setDislikes(new BigDecimal(0));
		post.setLikedBy(new ArrayList<String>());
		post.setDislikedBy(new ArrayList<String>());
		Post savedPost = postRepository.save(post);
		if (null == savedPost) {
			List<String> messages = new ArrayList<String>();
			messages.add("Could not create post");
			postResponse.setResponseStatus(CommonResponseUtil.buildFailureResponseStatus(messages));
		} else {
			postResponse.setResponseStatus(CommonResponseUtil.buildSuccessResponseStatus());
			List<Post> posts = new ArrayList<Post>();
			posts.add(savedPost);
			postResponse.setPosts(posts);
		}
		return ResponseEntity.ok(postResponse);
	}

	@Override
	public ResponseEntity<PostResponse> findPostById(String id) {
		PostResponse postResponse = new PostResponse();
		Optional<Post> post = postRepository.findById(id);
		if (null == post.get()) {
			List<String> messages = new ArrayList<String>();
			messages.add("Could not find a post for given ID");
			postResponse.setResponseStatus(CommonResponseUtil.buildFailureResponseStatus(messages));
		} else {
			postResponse.setResponseStatus(CommonResponseUtil.buildSuccessResponseStatus());
			List<Post> posts = new ArrayList<Post>();
			posts.add(post.get());
			postResponse.setPosts(posts);
		}

		return ResponseEntity.ok(postResponse);
	}

	@Override
	public ResponseEntity<PostResponse> findAllPostsByUsername(String username) {
		PostResponse postResponse = new PostResponse();
		List<Post> posts = postRepository.findAllPostsByPostedBy(username);

		if (null == posts) {
			List<String> messages = new ArrayList<String>();
			messages.add("Could not find any posts for given user");
			postResponse.setResponseStatus(CommonResponseUtil.buildFailureResponseStatus(messages));
		} else {
			postResponse.setResponseStatus(CommonResponseUtil.buildSuccessResponseStatus());
			postResponse.setPosts(posts);
		}

		return ResponseEntity.ok(postResponse);
	}

	@Override
	public ResponseEntity<PostResponse> likePost(String id, String username) {
		PostResponse postResponse = new PostResponse();
		Optional<Post> postOptional = postRepository.findById(id);
		if (postOptional.isPresent()) {
			Post post = postOptional.get();
			if (null == userRepository.findByUsername(username)) {
				List<String> messages = new ArrayList<String>();
				messages.add("No such user exits");
				postResponse.setResponseStatus(CommonResponseUtil.buildFailureResponseStatus(messages));
			} else if (username.equals(post.getPostedBy())) {
				List<String> messages = new ArrayList<String>();
				messages.add("Creator of the post cannot like it");
				postResponse.setResponseStatus(CommonResponseUtil.buildFailureResponseStatus(messages));
			} else {
				if (!post.getDislikedBy().isEmpty() && post.getDislikedBy().contains(username)) {
					post.getDislikedBy().remove(username);
					post.setDislikes(post.getDislikes().subtract(BigDecimal.ONE));
				}
				if (!post.getLikedBy().contains(username)) {
					post.getLikedBy().add(username);
					post.setLikes(post.getLikes().add(BigDecimal.ONE));
				}
				postRepository.save(post);
				postResponse.setResponseStatus(CommonResponseUtil.buildSuccessResponseStatus());
			}
		} else {
			List<String> messages = new ArrayList<String>();
			messages.add("Could not find any posts for given ID");
			postResponse.setResponseStatus(CommonResponseUtil.buildFailureResponseStatus(messages));
		}

		return ResponseEntity.ok(postResponse);
	}

	@Override
	public ResponseEntity<PostResponse> dislikePost(String id, String username) {
		PostResponse postResponse = new PostResponse();
		Optional<Post> postOptional = postRepository.findById(id);
		if (postOptional.isPresent()) {
			Post post = postOptional.get();
			if (null == userRepository.findByUsername(username)) {
				List<String> messages = new ArrayList<String>();
				messages.add("No such user exits");
				postResponse.setResponseStatus(CommonResponseUtil.buildFailureResponseStatus(messages));
			} else if (username.equals(post.getPostedBy())) {
				List<String> messages = new ArrayList<String>();
				messages.add("Creator of the post cannot like it");
				postResponse.setResponseStatus(CommonResponseUtil.buildFailureResponseStatus(messages));
			} else {
				if (!post.getLikedBy().isEmpty() && !post.getLikedBy().contains(username)) {
					post.getDislikedBy().remove(username);
					post.setDislikes(post.getDislikes().subtract(BigDecimal.ONE));
				}
				if (post.getDislikedBy().contains(username)) {
					post.getLikedBy().add(username);
					post.setLikes(post.getLikes().add(BigDecimal.ONE));
				}
				postRepository.save(post);
				postResponse.setResponseStatus(CommonResponseUtil.buildSuccessResponseStatus());
			}
		} else {
			List<String> messages = new ArrayList<String>();
			messages.add("Could not find any posts for given ID");
			postResponse.setResponseStatus(CommonResponseUtil.buildFailureResponseStatus(messages));
		}

		return ResponseEntity.ok(postResponse);
	}

}
