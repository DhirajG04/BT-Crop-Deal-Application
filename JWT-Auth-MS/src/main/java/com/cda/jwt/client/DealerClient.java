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

import com.cda.jwt.model.Dealer;


@FeignClient(name ="dealerfeign",url="http://localhost:9002/dealer")
public interface DealerClient {


	
		@PostMapping("/addDealer")
		@PreAuthorize(" hasRole('ADMIN') or hasRole('DEALER')")
		public ResponseEntity<Dealer> addDealer(@RequestHeader("Authorization") String token,@RequestBody Dealer dealer);
		
		@GetMapping("/getAllDealers")
		@PreAuthorize(" hasRole('ADMIN') or hasRole('DEALER')")
		public ResponseEntity < List < Dealer >> getDealers(@RequestHeader("Authorization") String token);
	
		@GetMapping("/getDealer/{dealerId}")
		@PreAuthorize(" hasRole('ADMIN') or hasRole('DEALER')")
		public ResponseEntity <Dealer> getDealerById(@RequestHeader("Authorization") String token, @PathVariable String dealerId);	
		
		@PutMapping("/updateDealer/{dealerId}")
		@PreAuthorize(" hasRole('ADMIN') or hasRole('DEALER')")
		public ResponseEntity < Dealer > updateDealer(@RequestHeader("Authorization") String token, @PathVariable String dealerId, @RequestBody Dealer dealer);
		
		@DeleteMapping("/deleteDealer/{dealerId}")
		@PreAuthorize(" hasRole('ADMIN') or hasRole('DEALER')")
		public HttpStatus deleteDealerById(@RequestHeader("Authorization") String token, @PathVariable String dealerId);
}
