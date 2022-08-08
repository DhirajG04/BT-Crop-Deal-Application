package com.cda.crop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cda.crop.model.Crop;

public interface CropRepository extends MongoRepository<Crop, String> {

}
