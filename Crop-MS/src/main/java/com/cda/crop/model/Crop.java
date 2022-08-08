package com.cda.crop.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="crop")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crop {

	@Id
	private String cropId;
	private String cropName;
	private String cropType;
	private String cropQty;
	private String price;
	private String uploader;
		
}
