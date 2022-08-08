package com.cda.dealer.controller;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cda.dealer.exception.ResourceNotFoundException;
import com.cda.dealer.model.Dealer;
import com.cda.dealer.service.DealerService;

@RestController
@RequestMapping("/dealer")
public class DealerController {
	
	
	@Autowired
    private DealerService dealerService;
	
	
	@PostMapping("/addDealer")
	public ResponseEntity<Dealer> addDealer(@RequestBody Dealer dealer){
		return ResponseEntity.ok().body(this.dealerService.addDealer(dealer));
	}

	
	@GetMapping("/getAllDealers")
	public ResponseEntity < List < Dealer >> getDealers(){
		return ResponseEntity.ok().body(dealerService.getDealers());
	}


	@GetMapping("/getDealer/{dealerId}")
	public ResponseEntity <Dealer> getDealerById(@PathVariable String dealerId) throws ResourceNotFoundException {
		
			return ResponseEntity.ok().body(dealerService.getDealerById(dealerId));		
	}

	@PutMapping("/updateDealer/{dealerId}")
	public ResponseEntity < Dealer > updateDealer(@PathVariable String dealerId, @RequestBody Dealer dealer) throws ResourceNotFoundException {
		dealer.setDealerId(dealerId);
		return ResponseEntity.ok().body(this.dealerService.updateDealer(dealer));
    
	}
	
	
	@DeleteMapping("/deleteDealer/{dealerId}")
	public HttpStatus deleteDealerById(@PathVariable String dealerId) throws ResourceNotFoundException {
		this.dealerService.deleteDealerById(dealerId);
		return HttpStatus.OK;
	}

	
	
}
