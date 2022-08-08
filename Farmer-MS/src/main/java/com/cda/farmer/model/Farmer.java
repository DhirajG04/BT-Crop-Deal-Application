package com.cda.farmer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection = "farmers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Farmer {

	@Id
	private String farmerId;
	private String farmerName;
	private String farmerEmail;
	private String farmerContactNo;
	private String farmerAccNo;
	private String farmerPass;
	
}