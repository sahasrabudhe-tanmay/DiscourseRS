package com.tanmay.discourse.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tanmay.discourse.service.ImageService;

@CrossOrigin
@RestController
@RequestMapping("/discourse-rs/image")
public class ImageController {
	
	@Autowired
	private ImageService imageService;

	@PostMapping("/upload")
	public String uploadImage(@RequestParam String title, @RequestParam MultipartFile image) throws IOException {
		
		return imageService.uploadImage(title, image);
	}

}
