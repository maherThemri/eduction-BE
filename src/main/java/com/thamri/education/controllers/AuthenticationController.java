package com.thamri.education.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thamri.education.dto.AuthenticationRequest;
import com.thamri.education.dto.AuthenticationResponse;
import com.thamri.education.dto.UserDto;
import com.thamri.education.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto dto){
		return ResponseEntity.ok(userService.register(dto));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(userService.authenticate(request));
		
	}
}
