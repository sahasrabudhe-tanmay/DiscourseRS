package com.tanmay.discourse.service;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tanmay.discourse.model.Image;
import com.tanmay.discourse.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	ImageRepository imageRepository;

	@Override
	public String uploadImage(String title, MultipartFile file) throws IOException {
		Image image = new Image();
		image.setTitle(title);
		image.setImage(
			new Binary(BsonBinarySubType.BINARY, file.getBytes())
		);
		
		image = imageRepository.insert(image);
		return image.get_id();
	}

	@Override
	public Image getImage(String id) {
		return null;
	}

}
