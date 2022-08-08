package com.cda.farmer.service;

import java.util.List;

import com.cda.farmer.exception.ResourceNotFoundException;
import com.cda.farmer.model.Farmer;

public interface FarmerService {

	 Farmer addFarmer(Farmer farmer);
	 List<Farmer> getFarmers();
	 Farmer getFarmerById(String farmerId) throws ResourceNotFoundException;
	 Farmer updateFarmer(Farmer farmer) throws ResourceNotFoundException;
	 void deleteFarmerById(String farmerId) throws ResourceNotFoundException;
	
}
