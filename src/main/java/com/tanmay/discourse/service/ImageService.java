package com.tanmay.discourse.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.tanmay.discourse.model.Image;

public interface ImageService {
	
	public String uploadImage(String title, MultipartFile file) throws IOException;
	
	public Image getImage(String id);

}
