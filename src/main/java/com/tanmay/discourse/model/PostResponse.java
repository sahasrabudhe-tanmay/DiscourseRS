package com.tanmay.discourse.model;

import java.util.List;

public class PostResponse {
	
	private ResponseStatus responseStatus;
	private List<Post> posts;
	
	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

}
