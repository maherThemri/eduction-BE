package com.thamri.education.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role extends AbstractEntity {
	
	
	@Column(unique = true)
	private String nameRole;
	
	private boolean active;
	
	@OneToMany (mappedBy = "role")
	@JsonIgnore
	private List<User> users;
	
	

}
