package com.thamri.education.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thamri.education.dto.UserDto;
import com.thamri.education.services.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/add-user")
	public ResponseEntity<Long> saveUser(@RequestBody UserDto dto) {
		return ResponseEntity.ok(service.saveUser(dto));
	}

	@GetMapping("/get-user/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findUserById(id));
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return ResponseEntity.ok(service.getAllUsers());
	}

	@PatchMapping("/validate/{id}")
	public ResponseEntity<Long> validateUser(@PathVariable Long id) {
		return ResponseEntity.ok(service.validateUser(id));
	}

	@PatchMapping("/invalidate/{id}")
	public ResponseEntity<Long> invalidateUser(@PathVariable Long id) {
		return ResponseEntity.ok(service.invalidateUser(id));
	}

	@PostMapping("/uploadFS/{id}")
	public void uploadImageFS(@RequestParam("image") MultipartFile file, @PathVariable("id") Long id)
			throws IOException {
		UserDto user = service.findUserById(id);
		String oldImage = user.getImagePath();
		String newImage = (id + "-" + file.getOriginalFilename());
		if (oldImage != newImage) {

			user.setImagePath(newImage);
			Files.write(Paths.get(System.getProperty("user.home") + "/Desktop/uploads/" + user.getImagePath()),
					file.getBytes());
			if (oldImage != null) {
				try {
					Files.delete(Paths.get(System.getProperty("user.home") + "/Desktop/uploads/" + oldImage));
				} catch (Exception e) {
					e.getMessage();
				}
				{

				}
			}
			service.saveUser(user);

		}

	}
	
	@GetMapping("/loadfromFS/{id}")
	public byte[] getImageFS(@PathVariable("id") Long id) throws IOException {

		UserDto user = service.findUserById(id);
		return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Desktop/uploads/" + user.getImagePath()));
	}

}
