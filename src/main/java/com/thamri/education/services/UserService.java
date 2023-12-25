package com.thamri.education.services;

import java.util.List;

import com.thamri.education.dto.AuthenticationRequest;
import com.thamri.education.dto.AuthenticationResponse;
import com.thamri.education.dto.UserDto;

public interface UserService {

	public List<UserDto> getAllUsers();
	public Long saveUser(UserDto dto);
	public UserDto findUserById(Long id);
	public Long validateUser(Long id);
	public Long invalidateUser(Long id);
	public AuthenticationResponse register(UserDto dto);
	public AuthenticationResponse authenticate(AuthenticationRequest request);
}
