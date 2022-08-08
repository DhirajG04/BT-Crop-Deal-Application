package com.cda.crop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cda.crop.exception.ResourceNotFoundException;
import com.cda.crop.model.Crop;
import com.cda.crop.repository.CropRepository;
import com.cda.crop.service.CropService;

@RestController
@RequestMapping("/crop")
public class CropController {
	
	@Autowired
    private CropService cropService;
	
	
	@PostMapping("/addCrop")
	public ResponseEntity<Crop> addCrop(@RequestBody Crop crop){
		return ResponseEntity.ok().body(this.cropService.addCrop(crop));
	}

	
	@GetMapping("/getAllCrops")
	public ResponseEntity < List < Crop >> getCrops(){
		return ResponseEntity.ok().body(cropService.getCrops());
	}


	@GetMapping("/getCrop/{cropId}")
	public ResponseEntity <Crop> getCropById(@PathVariable String cropId) throws ResourceNotFoundException {
		
			return ResponseEntity.ok().body(cropService.getCropById(cropId));		
	}

	@PutMapping("/updateCrop/{cropId}")
	public ResponseEntity < Crop > updateCrop(@PathVariable String cropId, @RequestBody Crop crop) throws ResourceNotFoundException {
		crop.setCropId(cropId);
		return ResponseEntity.ok().body(this.cropService.updateCrop(crop));
    
	}
	
	
	@DeleteMapping("/deleteCrop/{cropId}")
	public HttpStatus deleteCropById(@PathVariable String cropId) throws ResourceNotFoundException {
		this.cropService.deleteCropById(cropId);
		return HttpStatus.OK;
	}

}
