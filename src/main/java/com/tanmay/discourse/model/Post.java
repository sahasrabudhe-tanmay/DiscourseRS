package com.tanmay.discourse.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Posts")
public class Post {
	
	@Id
	private String id;
	private String title;
	private String description;
	private Image image;
	private String postedBy;
	private BigDecimal likes;
	private List<String> likedBy;
	private BigDecimal dislikes;
	private List<String> dislikedBy;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public String getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
	public BigDecimal getLikes() {
		return likes;
	}
	public void setLikes(BigDecimal likes) {
		this.likes = likes;
	}
	public List<String> getLikedBy() {
		return likedBy;
	}
	public void setLikedBy(List<String> likedBy) {
		this.likedBy = likedBy;
	}
	public BigDecimal getDislikes() {
		return dislikes;
	}
	public void setDislikes(BigDecimal dislikes) {
		this.dislikes = dislikes;
	}
	public List<String> getDislikedBy() {
		return dislikedBy;
	}
	public void setDislikedBy(List<String> dislikedBy) {
		this.dislikedBy = dislikedBy;
	}

}
