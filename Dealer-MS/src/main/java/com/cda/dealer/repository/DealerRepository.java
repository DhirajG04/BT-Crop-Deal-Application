package com.cda.dealer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cda.dealer.model.Dealer;

public interface DealerRepository extends MongoRepository<Dealer, String>{

}
