package com.thamri.education.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thamri.education.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	List<Role> findAllByActiveTrue();
}
