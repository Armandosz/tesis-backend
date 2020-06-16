/*
 * There are 3 necessary methods that MongoRepository supports.
 * */

package com.inmuebles.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.inmuebles.model.User;

public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}