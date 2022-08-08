package com.cda.jwt.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cda.jwt.client.CropClient;
import com.cda.jwt.client.DealerClient;
import com.cda.jwt.client.FarmerClient;
import com.cda.jwt.jwt.JwtUtils;
import com.cda.jwt.model.Crop;
import com.cda.jwt.model.Dealer;
import com.cda.jwt.model.ERole;
import com.cda.jwt.model.Farmer;
import com.cda.jwt.model.Role;
import com.cda.jwt.model.User;
import com.cda.jwt.repository.RoleRepository;
import com.cda.jwt.repository.UserRepository;
import com.cda.jwt.request.LoginRequest;
import com.cda.jwt.request.SignupRequest;
import com.cda.jwt.response.JwtResponse;
import com.cda.jwt.response.MessageResponse;
import com.cda.jwt.service.UserDetailsImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	CropClient cropClient;
	
	@Autowired
	DealerClient dealerClient;
	
	@Autowired
	FarmerClient farmerClient;
	

	@PostMapping("/signin")
	public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	
	//___________ Create new user's account_____________________________
	
		@PostMapping("/signup")
		public ResponseEntity<MessageResponse> registerUser(@RequestBody SignupRequest signUpRequest) {
			if (userRepository.existsByUsername(signUpRequest.getUsername())) {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: Username is already taken!"));
				}

			if (userRepository.existsByEmail(signUpRequest.getEmail())) {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: Email is already in use!"));
				}

			
			User user = new User(signUpRequest.getUsername(), 
								 signUpRequest.getEmail(),
								 encoder.encode(signUpRequest.getPassword()));

			Set<String> strRoles = signUpRequest.getRoles();
			Set<Role> roles = new HashSet<>();

			if (strRoles == null) {
				Role farmerRole = roleRepository.findByName(ERole.ROLE_FARMER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(farmerRole);
			} else {
				strRoles.forEach(role -> {
					switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);

						break;
					case "dealer":
						Role dealerRole = roleRepository.findByName(ERole.ROLE_DEALER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(dealerRole);

						break;
					default:
						Role farmerRole = roleRepository.findByName(ERole.ROLE_FARMER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(farmerRole);
					}
				});
			}

			user.setRoles(roles);
			userRepository.save(user);

			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		}

		
//_______________Farmer service end points_________________________
		
		@PostMapping("/addFarmer")
		public ResponseEntity<Farmer> addfarmer(@RequestHeader("Authorization") String token,@RequestBody Farmer farmer){
			return farmerClient.addfarmer(token, farmer);
		}
		
		
		@GetMapping("/getAllFarmers")
		public ResponseEntity < List < Farmer >> getfarmers(@RequestHeader("Authorization") String token){
			return farmerClient.getfarmers(token);
		}

		
		@GetMapping("/getFarmer/{farmerId}")
		public ResponseEntity <Farmer> getFarmerById(@RequestHeader("Authorization") String token, @PathVariable String farmerId){
			return farmerClient.getFarmerById(token, farmerId);
		}
			
		
		@PutMapping("/updateFarmer/{farmerId}")
		public ResponseEntity < Farmer > updateFarmer(@RequestHeader("Authorization") String token, @PathVariable String farmerId, @RequestBody Farmer farmer){
			return farmerClient.updateFarmer(token, farmerId, farmer);
		}	

		
		@DeleteMapping("/deleteFarmer/{farmerId}")
		public HttpStatus deleteFarmerById(@RequestHeader("Authorization") String token, @PathVariable String farmerId){
			return farmerClient.deleteFarmerById(token, farmerId);
		}

		
//_______________Dealer service end points_________________________
		
		@PostMapping("/addDealer")
		public ResponseEntity<Dealer> addDealer(@RequestHeader("Authorization") String token,@RequestBody Dealer dealer){
			return dealerClient.addDealer(token, dealer);
		}
		
		@GetMapping("/getAllDealers")
		public ResponseEntity < List < Dealer >> getDealers(@RequestHeader("Authorization") String token){
			return dealerClient.getDealers(token);
		}
	
		@GetMapping("/getDealer/{dealerId}")
		public ResponseEntity <Dealer> getDealerById(@RequestHeader("Authorization") String token, @PathVariable String dealerId){
			return dealerClient.getDealerById(token, dealerId);
		}
		
		@PutMapping("/updateDealer/{dealerId}")
		public ResponseEntity < Dealer > updateDealer(@RequestHeader("Authorization") String token, @PathVariable String dealerId, @RequestBody Dealer dealer){
			return dealerClient.updateDealer(token, dealerId, dealer);
		}
		
		@DeleteMapping("/deleteDealer/{dealerId}")
		public HttpStatus deleteDealerById(@RequestHeader("Authorization") String token, @PathVariable String dealerId) {
			return dealerClient.deleteDealerById(token, dealerId);
		}

	
//		_______________Crop service end points_________________________	
		
		@PostMapping("/addCrop")
		public ResponseEntity<Crop> addcrop(@RequestHeader("Authorization") String token, @RequestBody Crop crop){
			return cropClient.addcrop(token, crop);
		}

		@GetMapping("/getAllCrops")
		public ResponseEntity < List < Crop >> getCrops(@RequestHeader("Authorization") String token){
			return cropClient.getCrops(token);
		}

		@GetMapping("/getCrop/{cropId}")
		public ResponseEntity <Crop> getCropById(@RequestHeader("Authorization") String token, @PathVariable String cropId){
			return cropClient.getCropById(token, cropId);
		}
		
		@PutMapping("/updateCrop/{cropId}")
		public ResponseEntity < Crop > updateCrop(@RequestHeader("Authorization") String token, @PathVariable String cropId, @RequestBody Crop crop){
			return cropClient.updateCrop(token, cropId, crop);
		}

		@DeleteMapping("/deleteCrop/{cropId}")
		public HttpStatus deleteCropById(@RequestHeader("Authorization") String token, @PathVariable String cropId) {
			return cropClient.deleteCropById(token, cropId);
		}

}





