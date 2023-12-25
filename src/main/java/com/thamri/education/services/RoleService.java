package com.thamri.education.services;

import java.util.List;

import com.thamri.education.dto.RoleDto;

public interface RoleService {

	public List<RoleDto>getAllRoles();
	public List<RoleDto>getAllRolesActive();
	public RoleDto findById(Long idRole);
	public Long saveRole(RoleDto obj);
	public Long validateRole(Long idRole);
	public Long invalidateRole(Long idRole);
	
}
