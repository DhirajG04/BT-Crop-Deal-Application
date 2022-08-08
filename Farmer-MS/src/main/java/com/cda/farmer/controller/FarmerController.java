package com.cda.farmer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.cda.farmer.client.AuthClient;
import com.cda.farmer.exception.ResourceNotFoundException;
import com.cda.farmer.model.Farmer;
import com.cda.farmer.service.FarmerService;

@RestController
@RequestMapping("/farmer")
public class FarmerController {


	
	@Autowired
    private FarmerService farmerService;
	
	
	@PostMapping("/addFarmer")
	public ResponseEntity<Farmer> addfarmer(@RequestBody Farmer farmer) {
		return ResponseEntity.ok().body(this.farmerService.addFarmer(farmer));
	}

	
	@GetMapping("/getAllFarmers")
	public ResponseEntity < List < Farmer >> getfarmers() {
		return ResponseEntity.ok().body(farmerService.getFarmers());
	}


	@GetMapping("/getFarmer/{farmerId}")
	public ResponseEntity <Farmer> getFarmerById(@PathVariable String farmerId) throws ResourceNotFoundException {
		
			return ResponseEntity.ok().body(farmerService.getFarmerById(farmerId));		
	}

	@PutMapping("/updateFarmer/{farmerId}")
	public ResponseEntity < Farmer > updateFarmer(@PathVariable String farmerId, @RequestBody Farmer farmer) throws ResourceNotFoundException {
		farmer.setFarmerId(farmerId);
		return ResponseEntity.ok().body(this.farmerService.updateFarmer(farmer));
    
	}
	
	
	@DeleteMapping("/deleteFarmer/{farmerId}")
	public HttpStatus deleteFarmerById(@PathVariable String farmerId) throws ResourceNotFoundException {
		this.farmerService.deleteFarmerById(farmerId);
		return HttpStatus.OK;
	}
	


}




