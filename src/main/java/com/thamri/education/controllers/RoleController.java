package com.thamri.education.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thamri.education.dto.RoleDto;
import com.thamri.education.services.RoleService;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/roles")

public class RoleController {
	@Autowired
	private RoleService service;
	
	@GetMapping("/")
	public ResponseEntity<List<RoleDto>>getAllRoles(){
		return ResponseEntity.ok(service.getAllRoles());
	}
	
	@GetMapping("/roles-active")
	public ResponseEntity<List<RoleDto>>getAllRolesActive(){
		return ResponseEntity.ok(service.getAllRolesActive());
	}
	
	@GetMapping("/get-role/{idRole}")
	public ResponseEntity<RoleDto> findById(@PathVariable Long idRole) {
		return ResponseEntity.ok(service.findById(idRole));
	}
	
	@PostMapping("/add-role")
	public ResponseEntity<Long> saveRole(@RequestBody RoleDto obj) {
		return ResponseEntity.ok(service.saveRole(obj));
	}
	
	@PatchMapping("/validate/{idRole}")
	public ResponseEntity<Long> validateRole(@PathVariable Long idRole) {
		return ResponseEntity.ok(service.validateRole(idRole));
	}
	
	@PatchMapping("/invalidate/{idRole}")
	public ResponseEntity<Long> invalidateRole(@PathVariable Long idRole) {
		return ResponseEntity.ok(service.invalidateRole(idRole));
	}
	

}
