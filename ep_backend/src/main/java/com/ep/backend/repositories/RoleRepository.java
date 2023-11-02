package com.ep.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ep.backend.entities.Role;

public interface RoleRepository extends JpaRepository<Role,String> {

}
