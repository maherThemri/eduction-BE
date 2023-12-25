package com.thamri.education.servicesImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.thamri.education.config.JwtUtils;
import com.thamri.education.dto.AuthenticationRequest;
import com.thamri.education.dto.AuthenticationResponse;
import com.thamri.education.dto.UserDto;
import com.thamri.education.models.Role;
import com.thamri.education.models.User;
import com.thamri.education.repositories.RoleRepository;
import com.thamri.education.repositories.UserRepository;
import com.thamri.education.services.UserService;
import com.thamri.education.validators.ObjectsValidator;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ObjectsValidator<UserDto> validator;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private  AuthenticationManager authManager;


	@Override
	public List<UserDto> getAllUsers() {

		return repository.findAll().stream().map(UserDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public Long saveUser(UserDto dto) {
		validator.validate(dto);
		User user= UserDto.toEntity(dto);
		return repository.save(user).getId();
	}

	@Override
	public UserDto findUserById(Long id) {
		
		return repository.findById(id)
				.map(UserDto::fromEntity)
				.orElseThrow(()-> new EntityNotFoundException("No user was found with the provided Id: " + id));
	}

	@Override
	public Long validateUser(Long id) {
		User user = repository.findById(id)
				.orElseThrow(()->new EntityNotFoundException("No user was found with the provided Id: "+ id));
		user.setActive(true);
		return repository.save(user).getId();
	}

	@Override
	public Long invalidateUser(Long id) {
		User user= repository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("No user found with the provided Id: "+id));
		user.setActive(false);
		return repository.save(user).getId();
	}

	@Override
	@Transactional
	public AuthenticationResponse register(UserDto dto) {
		validator.validate(dto);
		User user= UserDto.toEntity(dto);
		// Assurez-vous que le rôle de l'utilisateur est correctement défini
	    Long roleId = dto.getIdRole();
	    Role role = roleRepository.findById(roleId)
	            .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + roleId));
	    
	    user.setRole(role);
		user.setPassword(encoder.encode(user.getPassword()));
		//user.setRole(user.getRole());
		var savedUser= repository.save(user);
		/*Map<String, Object> claims = new HashMap<>();
		claims.put("userId", savedUser.getId());
		claims.put("fullName", savedUser.getFirstName() +" "+ savedUser.getLastName());*/
		String token = jwtUtils.generateToken(savedUser);
		return AuthenticationResponse.builder().token(token).build();
		
	}

	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		final User user = repository.findByEmail(request.getEmail()).get();
		/*Map<String, Object> claims = new HashMap<>();
		claims.put("userId", user.getId());
		claims.put("fullName", user.getFirstName() +" "+ user.getLastName());*/
		final String token= jwtUtils.generateToken(user);
		return AuthenticationResponse.builder().token(token).build();
	}

}
