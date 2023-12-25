package com.thamri.education.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.thamri.education.models.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RoleDto {

	private Long id;
	
	@NotBlank(message="Name is null")
	@NotNull(message="name is null")
	@NotEmpty(message ="names is empty")
	@Size(min=3, message="Min length 3 charactere")
	private String nameRole;
	
	private boolean active;
	
	public static RoleDto fromEntity(Role role) {
		return RoleDto.builder()
				.id(role.getId())
				.nameRole(role.getNameRole())
				.active(role.isActive())
				.build();
	}
	
	public static Role toEntity(RoleDto dto) {
		return Role.builder()
				.id(dto.getId())
				.nameRole(dto.getNameRole())
				.active(dto.isActive())
				.build();
	}
}
