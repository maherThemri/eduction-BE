package com.thamri.education.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.thamri.education.models.Role;
import com.thamri.education.models.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDto {
	
	private Long id;
	
	@Size(min=3,max=12, message="Your first name is not valide ")
	private String firstName;
	
	@Size(min=3,max=12, message="Your last name is not valide ")
	private String lastName;
	
	@Email(message="Your email is not valid")
	private String email;
	
	@Size(min=6,max=12, message="Your password is not valide ")
	private String password;
	
	@Size(min=8,max=12, message="Your phone number is not valide ")
	private String phoneNumber;
	
	private boolean active;
	
	private String imagePath;
	
	private Long idRole;
	
	private String nameRole;
	
	public static UserDto fromEntity(User user) {
		return UserDto.builder()
				.id(user.getId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.password(user.getPassword())
				.phoneNumber(user.getPhoneNumber())
				.active(user.isActive())
				.imagePath(user.getImagePath())
				.idRole(user.getRole().getId())
				.nameRole(user.getRole().getNameRole())
				.build();
	}
	
	public static User toEntity(UserDto user) {
		return User.builder()
				.id(user.getId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.password(user.getPassword())
				.phoneNumber(user.getPhoneNumber())
				.active(user.isActive())
				.imagePath(user.getImagePath())
				.role(Role.builder().id(user.getIdRole()).build())
				.build();
	}

}
