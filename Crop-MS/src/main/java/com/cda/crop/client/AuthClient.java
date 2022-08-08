package com.cda.crop.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cda.crop.model.AuthResponse;

@FeignClient(name="Jwtauthorization",url="http://localhost:9999/api/auth")
public interface AuthClient {

	 @GetMapping("/validate")
	    public AuthResponse getValidity(@RequestHeader("Authorization") String token); 
}
