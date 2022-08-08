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

import com.cda.jwt.model.Crop;


@FeignClient(name = "cropfeign",url="http://localhost:9003/crop")
public interface CropClient {


	@PostMapping("/addCrop")
	@PreAuthorize(" hasRole('ADMIN') or hasRole('FARMER')")
	public ResponseEntity<Crop> addcrop(@RequestHeader("Authorization") String token, @RequestBody Crop crop);

	@GetMapping("/getAllCrops")
	@PreAuthorize(" hasRole('ADMIN') or hasRole('DEALER') or hasRole('FARMER')")
	public ResponseEntity < List < Crop >> getCrops(@RequestHeader("Authorization") String token);

	@GetMapping("/getCrop/{cropId}")
	@PreAuthorize(" hasRole('ADMIN') or hasRole('DEALER') or hasRole('FARMER')")
	public ResponseEntity <Crop> getCropById(@RequestHeader("Authorization") String token, @PathVariable String cropId);
	
	@PutMapping("/updateCrop/{cropId}")
	@PreAuthorize(" hasRole('ADMIN') or hasRole('FARMER')")
	public ResponseEntity < Crop > updateCrop(@RequestHeader("Authorization") String token, @PathVariable String cropId, @RequestBody Crop crop);

	@DeleteMapping("/deleteCrop/{cropId}")
	@PreAuthorize(" hasRole('ADMIN') or hasRole('FARMER')")
	public HttpStatus deleteCropById(@RequestHeader("Authorization") String token, @PathVariable String cropId);

}
