package com.cda.crop.service;

import java.util.List;

import com.cda.crop.exception.ResourceNotFoundException;
import com.cda.crop.model.Crop;


public interface CropService {

	 Crop addCrop(Crop crop);
	 List<Crop> getCrops();
	 Crop getCropById(String cropId) throws ResourceNotFoundException;
	 Crop updateCrop(Crop crop) throws ResourceNotFoundException;
	 void deleteCropById(String cropId) throws ResourceNotFoundException;
}
