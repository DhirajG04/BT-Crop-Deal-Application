package com.cda.farmer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cda.farmer.model.Farmer;

public interface FarmerRepository extends MongoRepository<Farmer, String> {

}
