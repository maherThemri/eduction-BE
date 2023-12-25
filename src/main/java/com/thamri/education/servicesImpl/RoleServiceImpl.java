package com.thamri.education.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thamri.education.dto.RoleDto;
import com.thamri.education.models.Role;
import com.thamri.education.repositories.RoleRepository;
import com.thamri.education.services.RoleService;
import com.thamri.education.validators.ObjectsValidator;

@Service
public class RoleServiceImpl implements RoleService {

	
	@Autowired
	private RoleRepository repository;
	@Autowired
	private ObjectsValidator<RoleDto> validator;

	@Override
	public List<RoleDto> getAllRoles() {
		
		return repository.findAll().stream().map(RoleDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public List<RoleDto> getAllRolesActive() {
		return repository.findAllByActiveTrue()
				.stream().map(RoleDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public RoleDto findById(Long idRole) {
		return repository.findById(idRole)
				.map(RoleDto::fromEntity)
				.orElseThrow(()-> new EntityNotFoundException("No role was found with the provided Id: " + idRole));
	}

	@Override
	public Long saveRole(RoleDto obj) {
		validator.validate(obj);
		Role role = RoleDto.toEntity(obj);
		return repository.save(role).getId();
	}

	@Override
	public Long validateRole(Long idRole) {
		Role role = repository.findById(idRole)
				.orElseThrow(()->new EntityNotFoundException("No role was found with the provided Id: "+ idRole));
		role.setActive(true);
		return repository.save(role).getId();
	}

	@Override
	public Long invalidateRole(Long idRole) {
		Role role = repository.findById(idRole)
				.orElseThrow(()->new EntityNotFoundException("No role was found with the provided Id: "+ idRole));
		role.setActive(false);
		return repository.save(role).getId();
	}
	
	

}
