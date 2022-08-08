package com.cda.crop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.crop.exception.ResourceNotFoundException;
import com.cda.crop.model.Crop;
import com.cda.crop.repository.CropRepository;


@Service
public class CropServiceImpl implements CropService{

	
	@Autowired
	private CropRepository cropRepository;
	
	
	
	@Override
    public Crop addCrop(Crop crop) {
        return cropRepository.save(crop);
    }

	
	@Override
	public List<Crop> getCrops() {
		return this.cropRepository.findAll();
	}

	
	 @Override
	 public Crop getCropById(String cropId) throws ResourceNotFoundException {

	        Optional < Crop > cropData = this.cropRepository.findById(cropId);

	        if (cropData.isPresent()) {
	            return cropData.get();
	        } else {
	            throw new ResourceNotFoundException("Record not found");
	        }
	    }

	 @Override
	 public Crop updateCrop(Crop crop) throws ResourceNotFoundException {
	        Optional < Crop > cropDetails = this.cropRepository.findById(crop.getCropId());

	        if (cropDetails.isPresent()) {
	        	Crop cropUpdate = cropDetails.get();
	        	cropUpdate.setCropId(crop.getCropId());
	        	cropUpdate.setCropName(crop.getCropName());
	        	cropUpdate.setCropType(crop.getCropType());
	        	cropUpdate.setCropQty(crop.getCropQty());
	        	cropUpdate.setPrice(crop.getPrice());
	        	cropUpdate.setUploader(crop.getUploader());
	        	cropRepository.save(cropUpdate);
	            return cropUpdate;
	        } else {
	            throw new ResourceNotFoundException("Record not found");
	        }
	    }


	@Override
    public void deleteCropById(String cropId) throws ResourceNotFoundException {
        Optional < Crop > cropDetails = this.cropRepository.findById(cropId);

        if (cropDetails.isPresent()) {
            this.cropRepository.deleteById(cropId);
        } else {
            throw new ResourceNotFoundException("Record not found");
        }

    }

}
