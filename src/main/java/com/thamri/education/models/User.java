package com.thamri.education.models;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends AbstractEntity implements UserDetails{

	private String firstName;
	
	private String lastName;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private String phoneNumber;
	
	private boolean active;
	
	private String imagePath;
	
	@ManyToOne
	@JoinColumn(name = "id_role")
	private Role role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Collections.singletonList(new SimpleGrantedAuthority(role.getNameRole()));
		 
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}
}
