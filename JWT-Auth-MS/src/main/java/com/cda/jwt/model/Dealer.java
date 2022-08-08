package com.cda.jwt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection="dealer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dealer {

	@Id
	private String dealerId;
	private String dealerName;
	private String dealerEmail;
	private String dealerContactNo;
	private String dealerPass;
	
		
		
}
