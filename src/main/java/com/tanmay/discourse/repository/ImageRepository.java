package com.tanmay.discourse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tanmay.discourse.model.Image;

public interface ImageRepository extends MongoRepository<Image, String> {

}
