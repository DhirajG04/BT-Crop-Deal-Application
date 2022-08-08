package com.cda.dealer.service;

import java.util.List;

import com.cda.dealer.exception.ResourceNotFoundException;
import com.cda.dealer.model.Dealer;



public interface DealerService {

	 Dealer addDealer(Dealer dealer);
	 List<Dealer> getDealers();
	 Dealer getDealerById(String dealerId) throws ResourceNotFoundException;
	 Dealer updateDealer(Dealer dealer) throws ResourceNotFoundException;
	 void deleteDealerById(String dealerId) throws ResourceNotFoundException;
	
}
