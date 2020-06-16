/*
 * This repository also extends MongoRepository and provides a finder method.
 * */
package com.inmuebles.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.inmuebles.model.ERole;
import com.inmuebles.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}