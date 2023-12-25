package com.thamri.education.servicesImpl.auth;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thamri.education.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return repository.findByEmail(email)
				.orElseThrow(()-> new EntityNotFoundException("No user found with the provided email :"));
	}

}
