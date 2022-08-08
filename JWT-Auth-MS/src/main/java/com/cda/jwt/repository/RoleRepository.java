package com.cda.jwt.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cda.jwt.model.ERole;
import com.cda.jwt.model.Role;

public interface RoleRepository extends MongoRepository<Role, String>  {

	 Optional<Role> findByName(ERole name);
}
