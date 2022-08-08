package com.cda.farmer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.farmer.exception.ResourceNotFoundException;
import com.cda.farmer.model.Farmer;
import com.cda.farmer.repository.FarmerRepository;


@Service
public class FarmerServiceImpl implements FarmerService {

	@Autowired
	private FarmerRepository farmerRepository;
	
	
	
	@Override
    public Farmer addFarmer(Farmer farmer) {
        return farmerRepository.save(farmer);
    }

	
	@Override
	public List<Farmer> getFarmers() {
		return this.farmerRepository.findAll();
	}

	
	 @Override
	 public Farmer getFarmerById(String farmerId) throws ResourceNotFoundException {

	        Optional < Farmer > farmerData = this.farmerRepository.findById(farmerId);

	        if (farmerData.isPresent()) {
	            return farmerData.get();
	        } else {
	            throw new ResourceNotFoundException("Record not found with id : ");
	        }
	    }

	 @Override
	 public Farmer updateFarmer(Farmer farmer) throws ResourceNotFoundException {
	        Optional < Farmer > farmerDetails = this.farmerRepository.findById(farmer.getFarmerId());

	        if (farmerDetails.isPresent()) {
	        	Farmer farmerUpdate = farmerDetails.get();
	        	farmerUpdate.setFarmerId(farmer.getFarmerId());
	        	farmerUpdate.setFarmerName(farmer.getFarmerName());
	        	farmerUpdate.setFarmerEmail(farmer.getFarmerEmail());
	        	farmerUpdate.setFarmerContactNo(farmer.getFarmerContactNo());
	        	farmerUpdate.setFarmerAccNo(farmer.getFarmerAccNo());
	        	farmerUpdate.setFarmerPass(farmer.getFarmerPass());
	        	farmerRepository.save(farmerUpdate);
	            return farmerUpdate;
	        } else {
	            throw new ResourceNotFoundException("Record not found with id : ");
	        }
	    }


	@Override
    public void deleteFarmerById(String farmerId) throws ResourceNotFoundException {
        Optional < Farmer > farmerDetails = this.farmerRepository.findById(farmerId);

        if (farmerDetails.isPresent()) {
            this.farmerRepository.deleteById(farmerId);
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + farmerId);
        }

    }

}
