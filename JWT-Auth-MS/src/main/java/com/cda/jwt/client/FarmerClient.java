package com.cda.jwt.client;

import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cda.jwt.model.Farmer;



@FeignClient(name = "farmerfeign",url="http://localhost:9001/farmer")
public interface FarmerClient {

	   
	@PostMapping("/addFarmer")
	@PreAuthorize(" hasRole('ADMIN') or hasRole('FARMER')")
	public ResponseEntity<Farmer> addfarmer(@RequestHeader("Authorization") String token,@RequestBody Farmer farmer);
	
	
	@GetMapping("/getAllFarmers")
	@PreAuthorize(" hasRole('ADMIN') or hasRole('FARMER')")
	public ResponseEntity < List < Farmer >> getfarmers(@RequestHeader("Authorization") String token);

	
	@GetMapping("/getFarmer/{farmerId}")
	@PreAuthorize(" hasRole('ADMIN') or hasRole('FARMER')")
	public ResponseEntity <Farmer> getFarmerById(@RequestHeader("Authorization") String token, @PathVariable String farmerId);
		
	
	@PutMapping("/updateFarmer/{farmerId}")
	@PreAuthorize(" hasRole('ADMIN') or hasRole('FARMER')")
	public ResponseEntity < Farmer > updateFarmer(@RequestHeader("Authorization") String token, @PathVariable String farmerId, @RequestBody Farmer farmer);	

	
	@DeleteMapping("/deleteFarmer/{farmerId}")
	@PreAuthorize(" hasRole('ADMIN') or hasRole('FARMER')")
	public HttpStatus deleteFarmerById(@RequestHeader("Authorization") String token, @PathVariable String farmerId);
		
}
