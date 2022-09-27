package com.neosoft.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.poc.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}